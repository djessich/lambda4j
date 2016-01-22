<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>

<#-- parse only if lambda arity is greater than 2 -->
<#if (lambda.arity >= 2)>
    <#-- search for correct input lambda of only method -->
    <#assign inputLambda = LambdaUtils.search(lambda.type, 1, lambda.primitive, lambda.throwable)>
    <#-- set a list of textual representation for numbers -->
    <#assign numbers = ["first", "second", "third"]>
    <#-- set argument lists -->
    <#assign argumentTypes = [lambda.inputOneType!"", lambda.inputTwoType!"", lambda.inputThreeType!""]>
    <#assign argumentNames = [types.buildParameterName(lambda.inputOneType!""), types.buildParameterName(lambda.inputTwoType!""),types.buildParameterName(lambda.inputThreeType!"")]>
    <#-- loop over range (which depends on arity) and print only method -->
    <#list 0..!lambda.arity as arity>
        <#assign number = numbers[arity?index]>
        <#assign capitalizedNumber = number?cap_first>
        <#assign argumentType = argumentTypes[arity?index]>
        <#assign argumentName = argumentNames[arity?index]>
        <@onlyMethod number capitalizedNumber inputLambda argumentType argumentName/>
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
static ${genericParameterTypeString} ${lambda.name}${genericParameterTypeString} only${capitalizedNumber}(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeString(inputLambda, argumentType)} ${lambda.type.simpleName}) {
    Objects.requireNonNull(${lambda.type.simpleName});
    return (${parameterNameString}) -> ${lambda.type.simpleName}.${lambda.type.method}(${argumentName});
}
</#macro>
<#-- @formatter:on -->