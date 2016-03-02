<#-- @formatter:off -->
<#import "./classLambda.ftl" as super>

<#-- parse only if lambda is of type function -->
<#if LambdaUtils.isOfTypeSupplier(lambda)>
    <#assign codomain = LambdaUtils.searchByReturnType(lambda.type, lambda.arity, Object, lambda.throwable, false)>
    <@.namespace.supplierClassJavadoc codomain/>
</#if>

<#-- a helper macro to centralize classSupplier Javadoc and to avoid unnecessary indenting -->
<#macro supplierClassJavadoc codomain>
/**
 * Represents a supplier of ${super.javadocReturnString()}results${super.javadocThrowableString()}.
<#if helpers.isPrimitive(lambda.returnType)>
 * This is a ${super.javadocSpecializationString()} specialization of {@link ${codomain.name}}.
</#if>
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #${super.javadocLambdaMethodLinkString()}}.
 *
<#include "./paramGenericInput.ftl">
<#include "apiNoteJdkLambda.ftl">
 * @see ${codomain.name}
 */
</#macro>
<#-- @formatter:on -->