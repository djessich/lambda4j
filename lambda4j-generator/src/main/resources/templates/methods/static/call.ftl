<#-- @formatter:off -->
<#import "../../utils/helpers.ftl" as helpers>
<#import "../../utils/throwable.ftl" as throwable>
<#import "../../utils/types.ftl" as types>

<#-- search for correct input lambda which represents the same lambda as this one, but may be from jdk if such one exists -->
<#assign inputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, lambda.throwable, true)>

/**
 * Calls the given {@link ${inputLambda.name}} with the given argument${helpers.s()} and returns its result.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param ${lambda.type.simpleName} The ${lambda.type.simpleName} to be called
<#include "../../javadoc/paramArgumentInput.ftl">
<@.namespace.printJavadocReturnIfNotVoidCallMethod/>
<#include "../../javadoc/throwsNullPointerException.ftl">
<#include "../../javadoc/throwsThrowable.ftl">
 */
static ${genericParameterTypeStringWithThrowableErasure} ${types.buildParameterType(lambda.returnType)} call(${annotation.nonnull} final ${inputLambda.name}${genericParameterTypeStringWithErasure} ${lambda.type.simpleName} ${(lambda.arity > 0)?then(", ${parameterString}", "")}) <@throwable.printThrowableDeclaration/> {
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