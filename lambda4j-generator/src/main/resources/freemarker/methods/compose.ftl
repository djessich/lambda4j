<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- search for correct input lambdas depending on lambda arity -->
<#if (lambda.arity >= 1)>
    <#assign inputLambda1 = LambdaUtils.searchByReturnType(lambda.type, 1, lambda.firstInputType,  lambda.throwable)>
</#if>
<#if (lambda.arity >= 2)>
    <#assign inputLambda2 = LambdaUtils.searchByReturnType(lambda.type, 1, lambda.secondInputType,  lambda.throwable)>
</#if>
<#if (lambda.arity >= 3)>
    <#assign inputLambda3 = LambdaUtils.searchByReturnType(lambda.type, 1, lambda.thirdInputType,  lambda.throwable)>
</#if>
<#-- find correct output lambda which is able to handle object inputs only and returns object output -->
<#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object, Object, lambda.throwable)>

<@.namespace.composeMethod "A" "B" "C" inputLambda1 inputLambda2 inputLambda3 outputLambda/>

<#-- a helper macro to centralize compose method and to avoid unnecessary indenting -->
<#macro composeMethod generic1 generic2 generic3 inputLambda1 = "" inputLambda2 = "" inputLambda3 = "" outputLambda = "">
/**
 * Returns a composed {@link ${outputLambda.name}} that first applies the {@code before} ${.namespace.javadocInputLambdaSimpleNamePlural()} to its input, and
 * then applies this ${lambda.type.simpleName} to the result.
<#if !lambda.throwable>
 * If evaluation of either function throws an exception, it is relayed to the caller of the composed function.
</#if>
 *
<@.namespace.javadocGenericInputComposeMethod generic1 generic2 generic3 inputLambda1 inputLambda2 inputLambda3 outputLambda/>
<@.namespace.javadocArgumentInputComposeMethod inputLambda1 inputLambda2 inputLambda3/>
 * @return A composed {@code ${outputLambda.name}} that first applies the {@code before} ${.namespace.javadocInputLambdaSimpleNamePlural()} to its input, and
 * then applies this ${lambda.type.simpleName} to the result.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${.namespace.buildGenericInputTypeString(outputLambda, generic1, generic2, generic3)} ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda, generic1, generic2, generic3)} compose(${.namespace.inputLambdasString(inputLambda1, inputLambda2, inputLambda3)}) {
    <#--${.namespace.inputLambdaChecking(1 inputLambda1)}-->
    <#--${.namespace.inputLambdaChecking(2 inputLambda2)}-->
    <#--${.namespace.inputLambdaChecking(3 inputLambda3)}-->
    ${.namespace.inputLambdaChecking(inputLambda1, inputLambda2, inputLambda3)}
    return (${types.buildParameterNameString(outputLambda, generic1, generic2, generic3)}) -> ${lambda.type.method}(${.namespace.callLambdasString(inputLambda1, inputLambda2, inputLambda3)});
}
</#macro>

<#-- creates lambda javadoc string which represents lambda.type.simpleName including plural if lambda has arity greater than 1 -->
<#function javadocInputLambdaSimpleNamePlural>
    <#if (lambda.arity <= 1)>
        <#return inputLambda1.type.simpleName>
    <#else>
        <#return inputLambda1.type.simpleName + "s">
    </#if>
</#function>

<#-- prints javadoc generic input parameters of compose method -->
<#macro javadocGenericInputComposeMethod generic1 generic2 generic3 inputLambda1 = "" inputLambda2 = "" inputLambda3 = "" outputLambda = "">
<#if (lambda.arity >= 1) && inputLambda1?has_content>
 * @param <${generic1}> The type of the argument to the ${helpers.first()} given ${inputLambda1.type.simpleName}, and of composed ${outputLambda.type.simpleName}
</#if>
<#if (lambda.arity >= 2) && inputLambda2?has_content>
 * @param <${generic2}> The type of the argument to the second given ${inputLambda2.type.simpleName}, and of composed ${outputLambda.type.simpleName}
</#if>
<#if (lambda.arity >= 3) && inputLambda3?has_content>
 * @param <${generic3}> The type of the argument to the third given ${inputLambda3.type.simpleName}, and of composed ${outputLambda.type.simpleName}
</#if>
</#macro>

<#-- prints javadoc input parameters of compose method -->
<#macro javadocArgumentInputComposeMethod inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
<#if (lambda.arity >= 1) && inputLambda1?has_content>
 * @param before1 The ${helpers.first()} ${inputLambda1.type.simpleName} to apply before this ${lambda.type.simpleName} is applied
</#if>
<#if (lambda.arity >= 2) && inputLambda2?has_content>
 * @param before2 The second ${inputLambda2.type.simpleName} to apply before this ${lambda.type.simpleName} is applied
</#if>
<#if (lambda.arity >= 3) && inputLambda3?has_content>
 * @param before3 The third ${inputLambda3.type.simpleName} to apply before this ${lambda.type.simpleName} is applied
</#if>
</#macro>

<#-- a helper function to build a generic input lambda string for compose operation -->
<#function buildGenericInputTypeString target generic1 generic2 generic3>
    <#local ret = "">
    <#if (target.arity > 0)>
        <#local ret = ret + "<" + types.buildParameterTypeString(target, generic1, generic2, generic3) + ">">
    </#if>
    <#return ret>
</#function>

<#-- a helper function to build an input lambdas string for compose operation -->
<#function inputLambdasString inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
    <#local ret = "">
    <#if (lambda.arity >= 1) && inputLambda1?has_content>
        <#local ret = ret + '${annotation.nonnull} final ${inputLambda1.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda1, "A", "", "", lambda.firstInputType)} before1'>
    </#if>
    <#if (lambda.arity >= 2) && inputLambda2?has_content>
        <#local ret = ret + ', ${annotation.nonnull} final ${inputLambda2.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda2, "B", "", "", lambda.secondInputType)} before2'>
    </#if>
    <#if (lambda.arity >= 3) && inputLambda3?has_content>
        <#local ret = ret + ', ${annotation.nonnull} final ${inputLambda3.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda3, "C", "", "", lambda.thirdInputType)} before3'>
    </#if>
    <#return ret>
</#function>

<#-- a helper function which creates code for checking the given compose operations input argument -->
<#function inputLambdaChecking inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
    <#local ret = "">
    <#if (lambda.arity >= 1) && inputLambda1?has_content>
        <#local ret = ret + "Objects.requireNonNull(before1);">
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
<#function callLambdasString inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
    <#local ret = "">
    <#if (lambda.arity >= 1) && inputLambda1?has_content>
        <#local ret = ret + 'before1.${inputLambda1.type.method}(a)'>
    </#if>
    <#if (lambda.arity >= 2) && inputLambda2?has_content>
        <#local ret = ret + ', before2.${inputLambda2.type.method}(b)'>
    </#if>
    <#if (lambda.arity >= 3) && inputLambda3?has_content>
        <#local ret = ret + ', before3.${inputLambda3.type.method}(c)'>
    </#if>
    <#return ret>
</#function>
<#-- @formatter:on -->