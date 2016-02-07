<#-- @formatter:off -->
<#import "../utils/types.ftl" as types>
<#-- TODO find correct input and output lambda -->
<#-- set a map of textual representation for numbers and arguments -->
<#assign numbers = ["one", "two", "three"]>
<#-- loop over range (which depends on arity) and print only method -->
<#list 0..!lambda.arity as current>
    <#assign number = numbers[current?index]>
    <#if (current?index <= 1)>
        <#assign argument = "argument">
    <#else>
        <#assign argument = "arguments">
    </#if>
    <#assign newArity = ((lambda.arity - 1) - current?index)>
    <#assign outputLambda = lambda>
    <#if (newArity > 0)>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, newArity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, lambda.throwable)>
    <#else>
        <#assign outputLambda = LambdaUtils.searchByReturnType(LambdaUtils.getSupplierType(), newArity, lambda.returnType, lambda.throwable)>
    </#if>
    <#assign differenceLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, (lambda.arity - newArity), lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, lambda.throwable)>
    <@partialMethod outputLambda differenceLambda number argument newArity/>
${differenceLambda}
</#list>

<#-- a helper macro to centralize partial method and to avoid unnecessary indenting -->
<#macro partialMethod outputLambda differenceLambda number argument newArity>
/**
 * Applies this ${lambda.type.simpleName} partially to ${number} ${argument}. The result is a ${outputLambda.type.simpleName} of arity {@code ${newArity}};
 *
<#include "../javadoc/paramArgumentInput.ftl">
 * @return A partial application of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} partial(${types.buildParameterString(differenceLambda)}) {
    return (${types.buildParameterNameString(outputLambda)}) -> ${lambda.type.method}(${parameterNameString});
}
</#macro>
<#-- @formatter:on -->