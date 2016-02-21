<#-- @formatter:off -->
<#import "../utils/types.ftl" as types>

<#-- TODO difference of input and output lambda as partial method argument and closure parameters -->
<#if (!LambdaUtils.isOfTypeConsumer(lambda)) && (lambda.arity >= 2)>

<#-- set a map of textual representation for numbers and arguments -->
<#assign numbers = ["one", "two", "three"]>
<#-- loop over range (which depends on arity) and print only method -->
<#list 0..!lambda.arity as current>
    <#-- get current number in textual representation -->
    <#assign number = numbers[current?index]>
    <#-- if current is greater then one we need to apply plural -->
    <#if (current?index <= 1)>
        <#assign argument = "argument">
    <#else>
        <#assign argument = "arguments">
    </#if>
    <#-- calculate new arity for partial method lambda search operations -->
    <#assign newArity = ((lambda.arity - 1) - current?index)>
    <#-- search for correct input lambda, from which a parameter string is build in partial method -->
    <#assign inputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, (lambda.arity - newArity), lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, lambda.throwable)>
    <#-- search for correct output lambda; if calculated arity is greater than 0 then we need to search for all input types and return type; else find correct lambda with arity 0 -->
    <#if (newArity > 0)>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, newArity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, lambda.throwable)>
    <#else>
        <#assign outputLambda = LambdaUtils.searchByReturnType(LambdaUtils.getSupplierType(), newArity, lambda.returnType, lambda.throwable)>
    </#if>

    <#if (newArity == 2)>
        <#if outputLambda.firstInputType.equals(inputLambda.firstInputType)>
            <#assign outputLambda = LambdaUtils.copy(outputLambda)>
            ${outputLambda.setFirstInputType(lambda.secondInputType)}
            <#if (lambda.arity == 3)>
                ${outputLambda.setSecondInputType(lambda.thirdInputType)}
            </#if>
        </#if>
    </#if>

    <#if (newArity == 1)>
        <#if outputLambda.firstInputType.equals(inputLambda.firstInputType)>
            <#assign outputLambda = LambdaUtils.copy(outputLambda)>
            <#if (lambda.arity == 3)>
                ${outputLambda.setFirstInputType(lambda.thirdInputType)}
            </#if>
        </#if>
    </#if>

    <#-- print partial method -->
    <@partialMethod outputLambda inputLambda number argument newArity/>
</#list>

</#if>

<#-- a helper macro to centralize partial method and to avoid unnecessary indenting -->
<#macro partialMethod outputLambda inputLambda number argument newArity>
/**
 * Applies this ${lambda.type.simpleName} partially to ${number} ${argument}. The result is a ${outputLambda.type.simpleName} of arity {@code ${newArity}};
 *
<#include "../javadoc/paramArgumentInput.ftl">
 * @return A partial application of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} partial(${types.buildParameterString(inputLambda)}) {
    return (${types.buildParameterNameString(outputLambda)}) -> ${lambda.method}(${parameterNameString});
}
</#macro>
<#-- @formatter:on -->