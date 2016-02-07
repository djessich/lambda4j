<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#assign inputLambda = LambdaUtils.searchByFirstInputAndReturnType(lambda.type, 1, lambda.returnType, Object, lambda.throwable)>
<#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, Object, lambda.throwable)>
<@.namespace.andThenMethod inputLambda outputLambda/>

<#-- a helper macro to centralize andThen method and to avoid unnecessary indenting -->
<#macro andThenMethod inputLambda outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies the
 * {@code after} ${inputLambda.type.simpleName} to the result.
<#if !lambda.throwable>
 * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
</#if>
 *
 * @param <S> The type of return value from the {@code after} ${inputLambda.type.simpleName}, and of the composed ${outputLambda.type.simpleName}
 * @param after The ${inputLambda.type.simpleName} to apply after this ${lambda.type.simpleName} is applied
 * @return A composed {@code ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies the
 * {@code after} ${inputLambda.type.simpleName} to the result.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default <S> ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda, "", "", "", "S")}
andThen(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda, lambda.returnType, "", "", "S")} after) {
    Objects.requireNonNull(after);
    return (${parameterNameString}) -> after.${inputLambda.type.method}(${lambda.type.method}(${parameterNameString}));
}
</#macro>
<#-- @formatter:on -->