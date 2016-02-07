<#-- @formatter:off -->
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is not of type consumer -->
<#if !LambdaUtils.isOfTypeConsumer(lambda)>
    <#-- get input consumer lambda using a search for the global lambdas return type; throwable flag will also be handled -->
    <#assign inputLambda = LambdaUtils.searchByFirstInputType(LambdaUtils.getConsumerType(), 1, lambda.returnType, lambda.throwable)>
    <#-- get output consumer lambda using a search for all the input types of global lambda; throwable flag will also be handled -->
    <#assign outputLambda = LambdaUtils.searchByInputTypes(LambdaUtils.getConsumerType(), lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.throwable)>
    <#-- print consume method -->
    <@.namespace.consumeMethod inputLambda outputLambda/>
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
    Objects.requireNonNull(consumer);
    return (${parameterNameString}) -> ${inputLambda.type.simpleName}.${inputLambda.type.method}(${lambda.type.method}(${parameterNameString}));
}
</#macro>
<#-- @formatter:on -->