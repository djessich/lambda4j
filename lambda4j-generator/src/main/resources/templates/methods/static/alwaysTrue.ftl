<#-- @formatter:off -->

<#-- parse only if lambda is of type predicate -->
<#if LambdaUtils.isOfTypePredicate(lambda) || (LambdaUtils.isOfTypeSupplier(lambda) && (lambda.returnType == boolean))>
    <@.namespace.alwaysTrueMethod/>
</#if>

<#-- a helper macro to centralize alwaysTrue method and to avoid unnecessary indenting -->
<#macro alwaysTrueMethod>
/**
 * Returns a {@link ${lambda.name}} that always returns {@code true}.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @return A {@link ${lambda.name}} that always returns {@code true}.
 * @see #alwaysFalse()
 */
${annotation.nonnull}
static ${genericParameterTypeStringWithThrowableErasure} ${lambda.name}${genericParameterTypeString} alwaysTrue() {
    return (${parameterNameString}) -> true;
}
</#macro>
<#-- @formatter:on -->