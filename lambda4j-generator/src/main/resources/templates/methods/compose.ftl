<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is not of type supplier -->
<#if !LambdaUtils.isOfTypeSupplier(lambda)>
    <#-- search for correct input lambdas depending on lambda arity -->
    <#if (lambda.arity >= 1)>
        <#assign type = (lambda.firstInputType == boolean)?then(LambdaUtils.getPredicateType(), LambdaUtils.getFunctionType())>
        <#assign inputLambda1 = LambdaUtils.searchByFirstInputAndReturnType(type, 1, Object, lambda.firstInputType, lambda.throwable)>
    </#if>
    <#if (lambda.arity >= 2)>
        <#assign type = (lambda.secondInputType == boolean)?then(LambdaUtils.getPredicateType(), LambdaUtils.getFunctionType())>
        <#assign inputLambda2 = LambdaUtils.searchByFirstInputAndReturnType(type, 1, Object, lambda.secondInputType, lambda.throwable)>
    </#if>
    <#if (lambda.arity >= 3)>
        <#assign type = (lambda.thirdInputType == boolean)?then(LambdaUtils.getPredicateType(), LambdaUtils.getFunctionType())>
        <#assign inputLambda3 = LambdaUtils.searchByFirstInputAndReturnType(type, 1, Object, lambda.thirdInputType, lambda.throwable)>
    </#if>
    <#-- find correct output lambda which is able to handle object inputs only and returns lambda output, unless consumers which do not have outputs -->
    <#if LambdaUtils.isOfTypeConsumer(lambda)>
        <#assign outputLambda = LambdaUtils.searchByInputTypes(LambdaUtils.getConsumerType(), lambda.arity, Object, Object, Object, lambda.throwable)>
    <#else>
        <#assign type = (lambda.returnType == boolean)?then(LambdaUtils.getPredicateType(), LambdaUtils.getFunctionType())>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(type, lambda.arity, Object, Object, Object, lambda.returnType, lambda.throwable)>
    </#if>
    <#-- print compose method -->
    <@.namespace.composeMethod "A" "B" "C" outputLambda inputLambda1 inputLambda2 inputLambda3/>
</#if>

<#-- a helper macro to centralize compose method and to avoid unnecessary indenting -->
<#macro composeMethod generic1 generic2 generic3 outputLambda inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
/**
 * Returns a composed {@link ${outputLambda.name}} that first applies the {@code before} ${.namespace.javadocInputLambdaSimpleNamePlural()} to its input, and
 * then applies this ${lambda.type.simpleName} to the result.
<#if !lambda.throwable>
 * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
</#if>
 *
<@.namespace.javadocGenericInputComposeMethod generic1 generic2 generic3 outputLambda inputLambda1 inputLambda2 inputLambda3/>
<@.namespace.javadocArgumentInputComposeMethod inputLambda1 inputLambda2 inputLambda3/>
 * @return A composed {@code ${outputLambda.name}} that first applies the {@code before} ${.namespace.javadocInputLambdaSimpleNamePlural()} to its input, and
 * then applies this ${lambda.type.simpleName} to the result.
<#include "../javadoc/throwsNullPointerException.ftl">
 * @implNote The input argument of this method is able to handle every type.
 */
${annotation.nonnull}
default ${.namespace.buildGenericInputTypeString(outputLambda, generic1, generic2, generic3)} ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda, generic1, generic2, generic3)} compose(${.namespace.inputLambdasString(generic1, generic2, generic3, inputLambda1, inputLambda2, inputLambda3)}) {
    ${.namespace.inputLambdaChecking(inputLambda1, inputLambda2, inputLambda3)}
    return (${types.buildParameterNameString(outputLambda, generic1, generic2, generic3)}) -> ${lambda.type.method}(${.namespace.callLambdasString(inputLambda1, inputLambda2, inputLambda3)});
}
</#macro>

<#-- prints javadoc generic input parameters of compose method -->
<#macro javadocGenericInputComposeMethod generic1 generic2 generic3 outputLambda inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
<#if (lambda.arity >= 1) && inputLambda1?has_content>
 * @param <${generic1}> The type of the argument to the ${helpers.first()}given ${inputLambda1.type.simpleName}, and of composed ${outputLambda.type.simpleName}
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

<#-- a helper function to build a generic input lambda string for compose operation -->
<#function buildGenericInputTypeString target generic1 generic2 generic3>
    <#local ret = "">
    <#if (target.arity > 0)>
        <#local ret = ret + "<" + types.buildParameterTypeString(target, generic1, generic2, generic3) + ">">
    </#if>
    <#return ret>
</#function>

<#-- a helper function to build an input lambdas string for compose operation -->
<#function inputLambdasString generic1 generic2 generic3 inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
    <#local ret = "">
    <#if (lambda.arity >= 1) && inputLambda1?has_content>
        <#local ret = ret + '${annotation.nonnull} final ${inputLambda1.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda1, generic1, "", "", lambda.firstInputType)} before${helpers.number()}'>
    </#if>
    <#if (lambda.arity >= 2) && inputLambda2?has_content>
        <#local ret = ret + ', ${annotation.nonnull} final ${inputLambda2.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda2, generic2, "", "", lambda.secondInputType)} before2'>
    </#if>
    <#if (lambda.arity >= 3) && inputLambda3?has_content>
        <#local ret = ret + ', ${annotation.nonnull} final ${inputLambda3.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda3, generic3, "", "", lambda.thirdInputType)} before3'>
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
<#function callLambdasString inputLambda1 = "" inputLambda2 = "" inputLambda3 = "">
    <#local ret = "">
    <#if (lambda.arity >= 1) && inputLambda1?has_content>
        <#local ret = ret + 'before${helpers.number()}.${inputLambda1.type.method}(a)'>
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