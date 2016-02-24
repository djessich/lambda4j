<#-- @formatter:off -->
<#import "./classLambda.ftl" as super>

<#-- parse only if lambda is of type consumer -->
<#if LambdaUtils.isOfTypeConsumer(lambda)>
    <#assign codomain = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object, lambda.returnType, lambda.throwable)>
    <@.namespace.consumerClassJavadoc codomain/>
</#if>

<#-- a helper macro to centralize classConsumer Javadoc and to avoid unnecessary indenting -->
<#macro consumerClassJavadoc codomain>
/**
 * Represents an operation that accepts ${super.javadocDescriptionsString()} and returns no result${super.javadocThrowableString()}.
<#if helpers.isPrimitiveLambdaInput(lambda)>
 * This is a ${super.javadocSpecializationString()} specialization of {@link ${codomain.name}}.
</#if>
 * Unlike most other functional interfaces, {@code ${lambda.name}} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #${super.javadocLambdaMethodLinkString()}}.
 *
<#include "./paramGenericInput.ftl">
<#include "apiNoteJdkLambda.ftl">
 * @see ${codomain.name}
 */
</#macro>
<#-- @formatter:on -->
