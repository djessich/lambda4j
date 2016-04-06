<#-- @formatter:off -->
<#import "../../utils/filters.ftl" as filters>
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
<@.namespace.javadocGenericInputIdentityMethod/>
 * @return A {@code  ${lambda.name}} that always returns its input argument
 */
${annotation.nonnull}
static ${types.buildGenericInputParameterTypeStringWithThrowableErasure()} ${lambda.name}${types.buildGenericParameterTypeString(lambda, lambda.firstInputType, "", "", lambda.firstInputType)} identity() {
    return (${parameterNameString}) -> ${parameterNameString};
}
</#macro>

<#-- prints javadoc generic input parameters of identity method -->
<#macro javadocGenericInputIdentityMethod target = lambda>
<#if !helpers.isPrimitive(target.firstInputType)>
 * @param <${target.firstInputType}> The type of the argument to the ${target.type.simpleName} and of return from the ${target.type.simpleName}
</#if>
<#if (lambda.throwable)>
 * @param <${lambda.throwableType}> The type of the throwable to be thrown by this ${lambda.type.simpleName}
</#if>
</#macro>
<#-- @formatter:on -->