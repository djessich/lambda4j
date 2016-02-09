<#-- @formatter:off -->
<#import "../../utils/helpers.ftl" as helpers>
<#import "../../utils/types.ftl" as types>

<#-- TODO javadoc {@link Objects#equals(Object)} only for not primitive -->
<#-- parse only if lambda is of type predicate -->
<#if LambdaUtils.isOfTypePredicate(lambda)>
    <@.namespace.isEqualMethod/>
</#if>

<#-- a helper macro to centralize isEqual method and to avoid unnecessary indenting -->
<#macro isEqualMethod>
/**
 * Returns a {@link ${lambda.name}} that tests if the given arguments are <b>equal</b> to the ones of this ${lambda.type.simpleName}
 * according to {@link Objects#equals(Object)} method.
 *
<#include "../../javadoc/paramGenericInput.ftl">
<@.namespace.javadocArgumentInputisEqualMethod/>
 * @return A {@code ${lambda.name}} that tests if the given arguments are <b>equal</b> to the ones of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
static ${genericParameterTypeString} ${lambda.name}${genericParameterTypeString} isEqual(${.namespace.inputLambdasString()}) {
    return (${parameterNameString}) -> ${.namespace.callString()};
}
</#macro>

<#-- prints javadoc input parameters of isEqual method -->
<#macro javadocArgumentInputisEqualMethod>
<#if (lambda.arity >= 1)>
 * @param target${.namespace.numberSwitch()} The ${helpers.first(lambda)}reference with which to compare for equality, which may be {@code null}
</#if>
<#if (lambda.arity >= 2)>
 * @param target2 The second reference with which to compare for equality, which may be {@code null}
</#if>
<#if (lambda.arity >= 3)>
 * @param target3 The third reference with which to compare for equality, which may be {@code null}
</#if>
</#macro>

<#-- a helper function to build an input lambdas string for isEqual operation -->
<#function inputLambdasString>
    <#local ret = "">
    <#if (lambda.arity >= 1)>
        <#local ret = ret + helpers.isPrimitive(lambda.firstInputType)?then(lambda.firstInputType.typeSimpleName, annotation.nullable + " " + Object.typeSimpleName) + " target" + .namespace.numberSwitch()>
    </#if>
    <#if (lambda.arity >= 2)>
        <#local ret = ret + ", " + helpers.isPrimitive(lambda.secondInputType)?then(lambda.secondInputType.typeSimpleName, annotation.nullable + " " + Object.typeSimpleName) + " target2">
    </#if>
    <#if (lambda.arity >= 3)>
        <#local ret = ret + ", " + helpers.isPrimitive(lambda.thirdInputType)?then(lambda.thirdInputType.typeSimpleName, annotation.nullable + " " + Object.typeSimpleName) + " target3">
    </#if>
    <#return ret>
</#function>

<#-- a helper function to build the call string for isEqual operation -->
<#function callString>
    <#local ret = "">
    <#if (lambda.arity >= 1)>
        <#local parameterName = types.buildParameterName(lambda.firstInputType)>
        <#if helpers.isPrimitive(lambda.firstInputType)>
            <#local ret = ret + "(" + parameterName + " == target" + .namespace.numberSwitch() + ")">
        <#else>
            <#local ret = ret + "(" + parameterName + " == null ? target1 == null : " + parameterName + ".equals(target1))">
        </#if>
    </#if>
    <#if (lambda.arity >= 2)>
        <#local parameterName = types.buildParameterName(lambda.secondInputType)>
        <#if helpers.isPrimitive(lambda.secondInputType)>
            <#local ret = ret + " && (" + parameterName + " == target2)">
        <#else>
            <#local ret = ret + " && (" + parameterName + " == null ? target2 == null : " + parameterName + ".equals(target2))">
        </#if>
    </#if>
    <#if (lambda.arity >= 3)>
        <#local parameterName = types.buildParameterName(lambda.thirdInputType)>
        <#if helpers.isPrimitive(lambda.thirdInputType)>
            <#local ret = ret + " && (" + parameterName + " == target3)">
        <#else>
            <#local ret = ret + " && (" + parameterName + " == null ? target3 == null : " + parameterName + ".equals(target3))">
        </#if>
    </#if>
    <#return ret>
</#function>

<#-- a switch for printing "1" if lambda arity is greather than 1 -->
<#function numberSwitch>
    <#return (lambda.arity > 1)?then("1", "")>
</#function>
<#-- @formatter:on -->