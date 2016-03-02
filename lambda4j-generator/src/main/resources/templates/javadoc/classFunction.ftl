<#-- @formatter:off -->
<#import "./classLambda.ftl" as super>

<#-- parse only if lambda is of type function or operator -->
<#if LambdaUtils.isOfTypeFunction(lambda) || LambdaUtils.isOfTypeOperator(lambda)>
    <#assign codomain = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object, Object, lambda.throwable, false)>
    <@.namespace.functionClassJavadoc codomain/>
</#if>

<#-- a helper macro to centralize classFunction Javadoc and to avoid unnecessary indenting -->
<#macro functionClassJavadoc codomain>
/**
 * Represents an operation that accepts ${super.javadocDescriptionsString()} and produces a
 * ${super.javadocReturnString()}result${super.javadocThrowableString()}.
<#if helpers.isPrimitiveLambda(lambda)>
 * This is a ${super.javadocSpecializationString()} specialization of {@link ${codomain.name}}.
</#if>
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #${super.javadocLambdaMethodLinkString()}}.
 *
<#include "./paramGenericInput.ftl">
<#include "apiNoteJdkLambda.ftl">
 * @see ${codomain.name}
 */
</#macro>
<#-- @formatter:on -->