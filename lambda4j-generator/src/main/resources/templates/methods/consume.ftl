<#-- @formatter:off -->
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is not of type consumer or runnable (void return) -->
<#if !LambdaUtils.isOfTypeConsumer(lambda) && !LambdaUtils.isOfTypeRunnable(lambda)>
    <#-- get input consumer lambda using a search for the global lambdas return type as consumer lambda input; throwable flag will also be handled -->
    <#assign inputLambda = LambdaUtils.searchByFirstInputType(LambdaUtils.getConsumerType(), 1, lambda.returnType, lambda.throwable, true)>
    <#-- if lambda is of type supplier then print macro for suppliers; else print normal macro -->
    <#if LambdaUtils.isOfTypeSupplier(lambda)>
        <#assign outputLambda = LambdaUtils.searchByFirstInputType(LambdaUtils.getConsumerType(), 1, Object, lambda.throwable, false)>
        <#-- print consume method -->
        <@.namespace.consumeMethodForSupplierOnly inputLambda outputLambda/>
    <#else>
        <#-- get output consumer lambda using a search for all of the input types of global lambda; throwable flag will also be handled -->
        <#assign outputLambda = LambdaUtils.searchByInputTypes(LambdaUtils.getConsumerType(), lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.throwable, false)>
        <#-- print consume method -->
        <@.namespace.consumeMethod inputLambda outputLambda/>
    </#if>
</#if>

<#-- a helper macro to centralize consume method and to avoid unnecessary indenting -->
<#macro consumeMethod inputLambda outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that fist applies this ${lambda.type.simpleName} to its input, and then consumes the result
 * using the given {@link ${inputLambda.name}}.
<#if !lambda.throwable>
 * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
</#if>
 *
 * @param ${inputLambda.type.simpleName} The operation which consumes the result from this operation
 * @return A composed {@code ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then consumes the result
 * using the given {@code ${inputLambda.name}}.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} consume(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda, lambda.returnType)} ${inputLambda.type.simpleName}) {
    Objects.requireNonNull(${inputLambda.type.simpleName});
    return (${parameterNameString}) -> ${inputLambda.type.simpleName}.${inputLambda.method}(${lambda.method}(${parameterNameString}));
}
</#macro>

<#-- a helper macro to centralize consume method and to avoid unnecessary indenting, but only for suppliers -->
<#macro consumeMethodForSupplierOnly inputLambda outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that first gets the result from this ${lambda.type.simpleName}, and then consumes
 * the result using the given {@link ${inputLambda.name}}.
<#if !lambda.throwable>
 * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
</#if>
 *
 * @param ${inputLambda.type.simpleName} The operation which consumes the result from this operation
 * @return A composed {@code ${outputLambda.name}} that first gets the result from this ${lambda.type.simpleName}, and then consumes
 * the result using the given {@code ${inputLambda.name}}.
<#include "../javadoc/throwsNullPointerException.ftl">
 * @implNote Due to the fact that a {@link ${lambda.name}} receives no input, we do not need to pass an argument of a
 * particular type to the resulting {@code ${outputLambda.name}}. As a result, this method returns a {@code ${outputLambda.name}} of {@link Void},
 * whose argument is ignored. Therefore, the input parameter will always be {@code null} when the resulting consumer is
 * called with {@code Consumer#accept(Object)}.
 */
${annotation.nonnull}
default ${outputLambda.name}<Void> consume(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda, lambda.returnType)} ${inputLambda.type.simpleName}) {
    Objects.requireNonNull(${inputLambda.type.simpleName});
    return ignored -> ${inputLambda.type.simpleName}.${inputLambda.method}(${lambda.method}(${parameterNameString}));
}
</#macro>
<#-- @formatter:on -->