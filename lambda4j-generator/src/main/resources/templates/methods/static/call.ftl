<#-- @formatter:off -->
<#import "../../utils/helpers.ftl" as helpers>
<#import "../../utils/throwable.ftl" as throwable>
<#import "../../utils/types.ftl" as types>

/**
 * Calls the given {@link ${lambda.name}} with the given arguments and returns its result.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param ${lambda.type.simpleName} The ${lambda.type.simpleName} to be called
<#include "../../javadoc/paramArgumentInput.ftl">
<@.namespace.printJavadocReturnIfNotVoidCallMethod/>
<#include "../../javadoc/throwsNullPointerException.ftl">
<#include "../../javadoc/throwsThrowable.ftl">
 */
static ${genericParameterTypeString} ${types.buildParameterType(lambda.returnType)} call(${annotation.nonnull} final ${lambda.name}${genericParameterTypeStringWithErasure} ${lambda.type.simpleName} ${(lambda.arity > 0)?then(", ${parameterString}", "")}) <@throwable.printThrowableDeclaration/> {
    Objects.requireNonNull(${lambda.type.simpleName});
    ${helpers.printReturnIfNotVoid()} ${lambda.type.simpleName}.${lambda.method}(${parameterNameString});
}

<#-- a helper macro which prints javadoc return tag, if lambda is not of type consumer or runnable (no output) -->
<#macro printJavadocReturnIfNotVoidCallMethod target = lambda>
<#if !LambdaUtils.isOfTypeConsumer(target) && !LambdaUtils.isOfTypeRunnable(target)>
 * @return The result from the given {@code ${target.name}}.
</#if>
</#macro>
<#-- @formatter:on -->