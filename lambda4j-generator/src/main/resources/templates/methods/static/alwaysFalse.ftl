<#-- @formatter:off -->

<#-- parse only if lambda is of type predicate -->
<#if LambdaUtils.isOfTypePredicate(lambda) || (LambdaUtils.isOfTypeSupplier(lambda) && (lambda.returnType == boolean))>
    <@.namespace.alwaysFalseMethod/>
</#if>

<#-- a helper macro to centralize alwaysFalse method and to avoid unnecessary indenting -->
<#macro alwaysFalseMethod>
/**
 * Returns a {@link ${lambda.name}} that always returns {@code false}.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @return A {@link ${lambda.name}} that always returns {@code false}.
 * @see #alwaysTrue()
 */
${annotation.nonnull}
static ${genericParameterTypeStringWithThrowableErasure} ${lambda.name}${genericParameterTypeString} alwaysFalse() {
    return (${parameterNameString}) -> false;
}
</#macro>
<#-- @formatter:on -->