<#-- @formatter:off -->

<#-- parse only if lambda is of type predicate -->
<#if LambdaUtils.isOfTypePredicate(lambda)>
    <@.namespace.negateMethod/>
</#if>

<#-- a helper macro to centralize negate method and to avoid unnecessary indenting -->
<#macro negateMethod>
/**
 * Returns a {@link ${lambda.name}} that represents the logical negation of this one.
 *
 * @return A {@code ${lambda.name}} that represents the logical negation of this one.
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeString} negate() {
    return (${parameterNameString}) -> !${lambda.method}(${parameterNameString});
}
</#macro>
<#-- @formatter:on -->