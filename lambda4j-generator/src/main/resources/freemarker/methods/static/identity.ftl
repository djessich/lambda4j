<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>

<#-- parse only if lambda has arity 1 and return type -->
<#if lambda.arity == 1 && lambda.returnType??>
    <@.namespace.identityMethod/>
</#if>

<#-- a helper macro to centralize identity method and to avoid unnecessary indenting -->
<#macro identityMethod>
/**
 * Returns a {@link ${lambda.name}} that always returns its input argument.
 *
 * @return A {@code  ${lambda.name}} that always returns its input argument
 */
${annotation.nonnull}
static ${lambda.name} identity() {
    return (${parameterNameString}) -> ${parameterNameString};
}
</#macro>
<#-- @formatter:on -->