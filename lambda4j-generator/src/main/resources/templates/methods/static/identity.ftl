<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>

<#-- parse only if lambda has arity 1 and return type -->
<#if lambda.arity == 1 && lambda.returnType?? && lambda.firstInputType?? && (lambda.returnType.equals(lambda.firstInputType))>
    <@.namespace.identityMethod/>
</#if>

<#-- a helper macro to centralize identity method and to avoid unnecessary indenting -->
<#macro identityMethod>
/**
 * Returns a {@link ${lambda.name}} that always returns its input argument.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @return A {@code  ${lambda.name}} that always returns its input argument
 */
${annotation.nonnull}
static ${.namespace.buildGenericInputTypeString()} ${lambda.name}${types.buildGenericParameterTypeString(lambda, lambda.firstInputType, "", "", lambda.firstInputType)} identity() {
    return (${parameterNameString}) -> ${parameterNameString};
}
</#macro>

<#-- a helper function to build a generic input lambda string for compose operation -->
<#function buildGenericInputTypeString target = lambda other1 = "" other2 = "" other3 = "">
    <#local ret = "">
    <#if (target.arity > 0)>
        <#local ret = ret + "<" + types.buildParameterTypeString(target, other1, other2, other3) + ">">
    </#if>
    <#return ret>
</#function>
<#-- @formatter:on -->