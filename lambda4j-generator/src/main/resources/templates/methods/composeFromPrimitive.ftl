<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- parse only if lmabda is not of type supplier or runnable (no input) and has primitive input only -->
<#if !LambdaUtils.isOfTypeSupplier(lambda) && !LambdaUtils.isOfTypeRunnable(lambda) && helpers.isPrimitiveLambdaInput(lambda)>
    <#assign primitives = {
        "boolean":boolean,
        "byte":byte,
        "char":char,
        "double":double,
        "float":float,
        "int":int,
        "long":long,
        "short":short
    }>
    <#-- foreach primitive type print method -->
    <#list primitives?keys as key>
        <#-- get primitive type -->
        <#assign primitive = primitives[key]>
        <#-- search for correct input lambdas depending on global lambda arity with primitive input type and primitive return type (global lambda has primitive input) -->
        <#if (lambda.arity >= 1)>
            <#assign inputLambda1 = LambdaUtils.searchByFirstInputAndReturnType(1, primitive, lambda.firstInputType, lambda.throwable, true)>
        </#if>
        <#if (lambda.arity >= 2)>
            <#assign inputLambda2 = LambdaUtils.searchByFirstInputAndReturnType(1, primitive, lambda.secondInputType, lambda.throwable, true)>
        </#if>
        <#if (lambda.arity >= 3)>
            <#assign inputLambda3 = LambdaUtils.searchByFirstInputAndReturnType(1, primitive, lambda.thirdInputType, lambda.throwable, true)>
        </#if>
        <#-- search for correct output lambda which has primitive input only and the same return type as this lambda -->
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.arity, primitive, primitive, primitive, lambda.returnType, lambda.throwable, false)>
        <#-- print composeFromPrimitive method -->
        <@.namespace.composeFromPrimitiveMethod primitive.typeSimpleName outputLambda inputLambda1 inputLambda2 inputLambda3/>
    </#list>
</#if>

<#-- a helper macro to centralize compose method and to avoid unnecessary indenting -->
<#macro composeFromPrimitiveMethod primitiveType outputLambda, inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
/**
 * Returns a composed {@link ${outputLambda.name}} that first applies the {@code before} ${.namespace.javadocInputLambdaSimpleNamePlural()} to
 * its input, and then applies this ${lambda.type.simpleName} to the result.
<#if !lambda.throwable>
 * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
</#if>
 * This method is just convenience, to provide the ability to execute an operation which accepts {@code ${primitiveType}} input,
 * before this primitive ${lambda.type.simpleName} is executed.
 *
<@.namespace.javadocArgumentInputComposeMethod inputLambda1 inputLambda2 inputLambda3/>
 * @return A composed {@code ${outputLambda.name}} that first applies the {@code before} ${.namespace.javadocInputLambdaSimpleNamePlural()} to
 * its input, and then applies this ${lambda.type.simpleName} to the result.
<#include "../javadoc/throwsNullPointerException.ftl">
 * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code ${primitiveType}}.
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} composeFrom${primitiveType?cap_first}(${.namespace.inputLambdasString(inputLambda1, inputLambda2, inputLambda3)}) {
    ${.namespace.inputLambdaChecking(inputLambda1, inputLambda2, inputLambda3)}
    return (${types.buildParameterNameString(outputLambda)}) -> ${lambda.method}(${.namespace.callLambdasString(outputLambda, inputLambda1, inputLambda2, inputLambda3)});
}
</#macro>

<#-- prints javadoc input parameters of compose method -->
<#macro javadocArgumentInputComposeMethod inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
<#if (lambda.arity >= 1) && inputLambda1?has_content>
 * @param before${helpers.number()} The ${helpers.first()}${inputLambda1.type.simpleName} to apply before this ${lambda.type.simpleName} is applied
</#if>
<#if (lambda.arity >= 2) && inputLambda2?has_content>
 * @param before2 The second ${inputLambda2.type.simpleName} to apply before this ${lambda.type.simpleName} is applied
</#if>
<#if (lambda.arity >= 3) && inputLambda3?has_content>
 * @param before3 The third ${inputLambda3.type.simpleName} to apply before this ${lambda.type.simpleName} is applied
</#if>
</#macro>

<#-- creates lambda javadoc string which represents lambda.type.simpleName including plural if lambda has arity greater than 1 -->
<#function javadocInputLambdaSimpleNamePlural>
    <#if (lambda.arity <= 1)>
        <#return inputLambda1.type.simpleName>
    <#else>
        <#return inputLambda1.type.simpleName + "s">
    </#if>
</#function>

<#-- a helper function to build an input lambdas string for compose operation -->
<#function inputLambdasString inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
    <#local ret = "">
    <#if (lambda.arity >= 1) && inputLambda1?has_content>
        <#local ret = ret + '${annotation.nonnull} final ${inputLambda1.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda1, "", "", "", lambda.firstInputType)} before${helpers.number()}'>
    </#if>
    <#if (lambda.arity >= 2) && inputLambda2?has_content>
        <#local ret = ret + ', ${annotation.nonnull} final ${inputLambda2.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda2, "", "", "", lambda.secondInputType)} before2'>
    </#if>
    <#if (lambda.arity >= 3) && inputLambda3?has_content>
        <#local ret = ret + ', ${annotation.nonnull} final ${inputLambda3.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda3, "", "", "", lambda.thirdInputType)} before3'>
    </#if>
    <#return ret>
</#function>

<#-- a helper function which creates code for checking the given compose operations input argument -->
<#function inputLambdaChecking inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
    <#local ret = "">
    <#if (lambda.arity >= 1) && inputLambda1?has_content>
        <#local ret = ret + "Objects.requireNonNull(before${helpers.number()});">
    </#if>
    <#if (lambda.arity >= 2) && inputLambda2?has_content>
        <#local ret = ret + "Objects.requireNonNull(before2);">
    </#if>
    <#if (lambda.arity >= 3) && inputLambda3?has_content>
        <#local ret = ret + "Objects.requireNonNull(before3);">
    </#if>
    <#return ret>
</#function>

<#-- a helper function to build a string for calling the given input lambdas of compose operation -->
<#function callLambdasString outputLambda = "", inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
    <#local ret = "">
    <#if (lambda.arity >= 1) && inputLambda1?has_content>
        <#local ret = ret + 'before${helpers.number()}.${inputLambda1.method}(${types.buildParameterName(outputLambda.firstInputType, outputLambda)})'>
    </#if>
    <#if (lambda.arity >= 2) && inputLambda2?has_content>
        <#local ret = ret + ', before2.${inputLambda2.method}(${types.buildParameterName(outputLambda.secondInputType, outputLambda)})'>
    </#if>
    <#if (lambda.arity >= 3) && inputLambda3?has_content>
        <#local ret = ret + ', before3.${inputLambda3.method}(${types.buildParameterName(outputLambda.thirdInputType, outputLambda)})'>
    </#if>
    <#return ret>
</#function>
<#-- @formatter:on -->