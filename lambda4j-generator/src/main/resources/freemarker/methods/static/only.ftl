<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>
<#-- TODO find a proper way to search for lambda with primitive type first and inclusion of return type -->
<#-- parse only if lambda arity is greater than 2 -->
<#if (lambda.arity >= 2)>
    <#-- search for correct input lambda of only method -->
    <#assign inputLambda = LambdaUtils.searchByReturnType(lambda.type, 1, lambda.returnType, lambda.throwable)>
    <#-- set a list of textual representation for numbers -->
    <#assign numbers = ["first", "second", "third"]>
    <#-- set argument lists -->
    <#assign argumentTypes = [lambda.firstInputType!"", lambda.secondInputType!"", lambda.thirdInputType!""]>
    <#assign argumentNames = [types.buildParameterName(lambda.firstInputType!""), types.buildParameterName(lambda.secondInputType!""),types.buildParameterName(lambda.thirdInputType!"")]>
    <#-- loop over range (which depends on arity) and print only method -->
    <#list 0..!lambda.arity as current>
        <#assign number = numbers[current?index]>
        <#assign capitalizedNumber = number?cap_first>
        <#assign argumentType = argumentTypes[current?index]>
        <#assign argumentName = argumentNames[current?index]>
        <@.namespace.onlyMethod number capitalizedNumber inputLambda argumentType argumentName/>
    </#list>
</#if>

<#-- a helper macro to centralize only method and to avoid unnecessary indenting -->
<#macro onlyMethod number capitalizedNumber inputLambda argumentType argumentName>
/**
 * Creates a {@link ${lambda.name}} which uses the {@code ${number}} parameter of this one as argument for the given {@link ${inputLambda.name}}.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param ${lambda.type.simpleName} The ${lambda.type.simpleName} which accepts the {@code ${number}} parameter of this one
 * @return Creates a {@code ${lambda.name}} which uses the {@code ${number}} parameter of this one as argument for the given {@code ${inputLambda.name}}.
<#include "../../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
static ${genericParameterTypeString} ${lambda.name}${genericParameterTypeString} only${capitalizedNumber}(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda, argumentType)} ${lambda.type.simpleName}) {
    Objects.requireNonNull(${lambda.type.simpleName});
    return (${parameterNameString}) -> ${lambda.type.simpleName}.${lambda.type.method}(${argumentName});
}
</#macro>
<#-- @formatter:on -->