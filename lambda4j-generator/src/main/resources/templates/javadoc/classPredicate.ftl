<#-- @formatter:off -->
<#import "./classLambda.ftl" as super>

<#-- parse only if lambda is of type function -->
<#if LambdaUtils.isOfTypePredicate(lambda)>
    <#assign codomain = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object, lambda.returnType, lambda.throwable)>
    <@.namespace.predicateClassJavadoc codomain/>
</#if>

<#-- a helper macro to centralize classPredicate Javadoc and to avoid unnecessary indenting -->
<#macro predicateClassJavadoc codomain>
/**
 * Represents an predicate (boolean-valued function) of ${super.javadocDescriptionsString()}${super.javadocThrowableString()}.
<#if helpers.isPrimitiveLambdaInput(lambda)>
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