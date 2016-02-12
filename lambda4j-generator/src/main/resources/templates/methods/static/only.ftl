<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>

<#-- parse only if lambda arity is greater than 2 -->
<#if (lambda.arity >= 2)>
    <#-- set a list of textual representation for numbers -->
    <#assign numbers = ["first", "second", "third"]>
    <#-- set argument lists -->
    <#assign argumentTypes = [lambda.firstInputType!"", lambda.secondInputType!"", lambda.thirdInputType!""]>
    <#assign argumentNames = [types.buildParameterName(lambda.firstInputType!""), types.buildParameterName(lambda.secondInputType!""), types.buildParameterName(lambda.thirdInputType!"")]>
    <#-- loop over range (which depends on arity) and print only method -->
    <#list 0..!lambda.arity as current>
        <#-- search for correct input lambda of only method -->
        <#assign inputLambda = LambdaUtils.searchByFirstInputAndReturnType(1, argumentTypes[current?index], lambda.returnType, lambda.throwable)>
        <#-- get actual number from numbers array -->
        <#assign number = numbers[current?index]>
        <#-- get actual number from numbers array and capitalize first letter -->
        <#assign capitalizedNumber = number?cap_first>
        <#-- get lambda parameter -->
        <#assign argumentType = argumentTypes[current?index]>
        <#-- get lambda parameter and build its parameter name -->
        <#assign argumentName = argumentNames[current?index]>
        <#-- print only method -->
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
static ${genericParameterTypeString} ${lambda.name}${genericParameterTypeString} only${capitalizedNumber}(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda, argumentType)} ${inputLambda.type.simpleName}) {
    Objects.requireNonNull(${lambda.type.simpleName});
    return (${parameterNameString}) -> ${lambda.type.simpleName}.${lambda.type.method}(${argumentName});
}
</#macro>
<#-- @formatter:on -->