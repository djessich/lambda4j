<#-- @formatter:off -->

<#-- parse only if lambda is of type predicate -->
<#if LambdaUtils.isOfTypePredicate(lambda)>
    <#-- search for correct input lambda which represents the same lambda as this one, but may be from jdk if such one exists -->
    <#assign inputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, lambda.throwable, true)>
    <#-- print method -->
    <@.namespace.xorMethod inputLambda/>
</#if>

<#-- a helper macro to centralize xor method and to avoid unnecessary indenting -->
<#macro xorMethod inputLambda>
/**
 * Returns a composed {@link ${lambda.name}} that represents a short-circuiting logical XOR of this ${lambda.type.simpleName} and
 * another. Any exceptions thrown during evaluation of either ${lambda.type.simpleName} is relayed to the caller; if evaluation of
 * this {@code ${lambda.name}} throws an exception, the {@code other} ${inputLambda.type.simpleName} will not be evaluated.
 *
 * @param other A {@code ${lambda.name}} that will be logically-XORed with this one
 * @return A composed {@code ${lambda.name}} that represents the short-circuiting logical XOR of this ${lambda.type.simpleName} and
 * the {@code other} ${inputLambda.type.simpleName}.
<#include "../javadoc/throwsNullPointerException.ftl">
 * @see #and(${inputLambda.name})
 * @see #or(${inputLambda.name})
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeString} xor(${annotation.nonnull} final ${inputLambda.name}${genericParameterTypeStringWithErasure} other) {
    Objects.requireNonNull(other);
    return (${parameterNameString}) -> ${lambda.method}(${parameterNameString}) ^ other.${inputLambda.method}(${parameterNameString});
}
</#macro>
<#-- @formatter:on -->