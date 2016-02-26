<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/throwable.ftl" as throwable>
<#import "../utils/tuple.ftl" as tuple>
<#import "../utils/types.ftl" as types>

/**
 * Applies this ${lambda.type.simpleName} to the given arguments.
 *
<#include "../javadoc/paramArgumentInput.ftl">
<@.namespace.printJavadocReturnIfNotVoidLambdaMethod/>
<#include "../javadoc/throwsThrowable.ftl">
 */
${types.buildParameterType(lambda.returnType)} ${lambda.method}(${parameterString}) <@throwable.printThrowableDeclaration/>;

<#-- print tupled apply only if lambda has arity greater than 2 and at least two generics -->
<#if (lambda.arity >= 2) && !helpers.isPrimitive(lambda.firstInputType) && !helpers.isPrimitive(lambda.secondInputType)>
/**
 * Applies this ${lambda.type.simpleName} to the given tuple.
 *
 * @param tuple The tuple to be applied to the ${lambda.type.simpleName}
<@.namespace.javadocParamForTupleAndPrimitiveInput/>
<@.namespace.printJavadocReturnIfNotVoidLambdaMethod/>
<#include "../javadoc/throwsNullPointerException.ftl">
<#include "../javadoc/throwsThrowable.ftl">
<#include "../javadoc/seeTupleClass.ftl">
 */
default ${types.buildParameterType(lambda.returnType)} ${lambda.method}(${annotation.nonnull} ${tuple.printTuple()} tuple ${helpers.isPrimitive(lambda.thirdInputType)?then(", " + types.buildParameter(lambda.thirdInputType), "")}) <@throwable.printThrowableDeclaration/> {
    Objects.requireNonNull(tuple);
    ${helpers.printReturnIfNotVoid()} ${lambda.method}(${tuple.printTupleAccess()} ${helpers.isPrimitive(lambda.thirdInputType)?then(", " + types.buildParameterName(lambda.thirdInputType), "")});
}
</#if>

<#-- a helper macro which prints javadoc param tag, if lambda has tupled and primitive input -->
<#macro javadocParamForTupleAndPrimitiveInput target = lambda>
<#if !helpers.isPrimitive(target.firstInputType) && !helpers.isPrimitive(target.secondInputType) && helpers.isPrimitive(lambda.thirdInputType)>
 * @param ${types.buildParameterName(lambda.thirdInputType)} The primitive value to be applied to the ${lambda.type.simpleName}
</#if>
</#macro>

<#-- a helper macro which prints javadoc return tag, if lambda is not of type consumer or runnable (no output) -->
<#macro printJavadocReturnIfNotVoidLambdaMethod target = lambda>
<#if !LambdaUtils.isOfTypeConsumer(target) && !LambdaUtils.isOfTypeRunnable(target)>
<#--* @return The result from the given {@code ${lambda.name}}.-->
 * @return The return value from the ${target.type.simpleName}, which is its result.
</#if>
</#macro>

<#-- @formatter:on -->