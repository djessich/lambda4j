<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>

<#-- TODO Javadoc: generic operator has param for inputs and return type with same name (@param <T> ... 2x) -->
<#-- parse only if lambda arity is greater than 2 and is not of type comprator -->
<#if (lambda.arity >= 2) && !LambdaUtils.isOfTypeComparator(lambda)>
    <#-- set a list of textual representation for numbers -->
    <#assign numbers = ["first", "second", "third"]>
    <#-- set argument lists -->
    <#assign argumentTypes = [lambda.firstInputType!"", lambda.secondInputType!"", lambda.thirdInputType!""]>
    <#assign argumentNames = [types.buildParameterName(lambda.firstInputType!""), types.buildParameterName(lambda.secondInputType!""), types.buildParameterName(lambda.thirdInputType!"")]>
    <#-- loop over range (which depends on arity) and print only method -->
    <#list 0..!lambda.arity as current>
        <#-- get actual number from numbers array -->
        <#assign number = numbers[current?index]>
        <#-- get actual number from numbers array and capitalize first letter -->
        <#assign capitalizedNumber = number?cap_first>
        <#-- get lambda parameter -->
        <#assign argumentType = argumentTypes[current?index]>
        <#-- get lambda parameter and build its parameter name -->
        <#assign argumentName = argumentNames[current?index]>
        <#-- search for correct input lambda of only method, which is the global lambdas input type (depending on arity) and its return type -->
        <#assign type = (lambda.returnType.primitive && lambda.returnType.equals(argumentType))?then(LambdaUtils.getOperatorType(), lambda.type)>
        <#assign inputLambda = LambdaUtils.searchByFirstInputAndReturnType(type, 1, argumentType, lambda.returnType, lambda.throwable)>
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
 * @param ${inputLambda.type.simpleName} The ${inputLambda.type.simpleName} which accepts the {@code ${number}} parameter of this one
 * @return Creates a {@code ${lambda.name}} which uses the {@code ${number}} parameter of this one as argument for the given {@code ${inputLambda.name}}.
<#include "../../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
static ${genericParameterTypeString} ${lambda.name}${genericParameterTypeString} only${capitalizedNumber}(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda, argumentType)} ${inputLambda.type.simpleName}) {
    Objects.requireNonNull(${inputLambda.type.simpleName});
    return (${parameterNameString}) -> ${inputLambda.type.simpleName}.${inputLambda.method}(${argumentName});
}
</#macro>
<#-- @formatter:on -->