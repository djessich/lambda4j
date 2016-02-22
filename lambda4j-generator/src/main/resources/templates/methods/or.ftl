<#-- @formatter:off -->

<#-- parse only if lambda is of type predicate -->
<#if LambdaUtils.isOfTypePredicate(lambda)>
    <@.namespace.orMethod/>
</#if>

<#-- a helper macro to centralize and method or to avoid unnecessary indenting -->
<#macro orMethod>
/**
 * Returns a composed {@link ${lambda.name}} that represents a short-circuiting logical OR of this ${lambda.type.simpleName} and
 * another. When evaluating the composed ${lambda.type.simpleName}, if this ${lambda.type.simpleName} is {@code true}, then the {@code other}
 * ${lambda.type.simpleName} is not evaluated.
 * <p>
 * Any exceptions thrown during evaluation of either ${lambda.type.simpleName} is relayed to the caller; if evaluation of this
 * {@code ${lambda.name}} throws an exception, the {@code other} ${lambda.type.simpleName} will not be evaluated.
 *
 * @param other A {@code ${lambda.name}} that will be logically-ORed with this one
 * @return A composed {@code ${lambda.name}} that represents the short-circuiting logical OR of this ${lambda.type.simpleName} and the
 * {@code other} ${lambda.type.simpleName}.
<#include "../javadoc/throwsNullPointerException.ftl">
 * @see #and(${lambda.name})
 * @see #xor(${lambda.name})
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeString} or(${annotation.nonnull} final ${lambda.name}${genericParameterTypeStringWithErasure} other) {
    Objects.requireNonNull(other);
    return (${parameterNameString}) -> ${lambda.method}(${parameterNameString}) || other.${lambda.method}(${parameterNameString});
}
</#macro>
<#-- @formatter:on -->