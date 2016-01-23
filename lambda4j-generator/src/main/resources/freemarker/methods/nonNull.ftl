<#-- @formatter:off -->
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda return is not primitive -->
<#if (!helpers.isPrimitive(lambda.returnType))>
    <#-- generate generic string with return type wrapped in optional -->
    <#assign genericTypeStringWithOptionalReturn = types.buildGenericParameterTypeString(lambda, "", "", "", "Optional<" + lambda.returnType + ">")>
    <#-- print nonNull method -->
    <@.namespace.nonNullMethod genericTypeStringWithOptionalReturn/>
</#if>

<#-- a helper macro to centralize nonNull method and to avoid unnecessary indenting -->
<#macro nonNullMethod genericTypeStringWithOptionalReturn>
/**
 * Converts this ${lambda.type.simpleName} to an equal ${lambda.type.simpleName}, which ensures that its result is not
 * {@code null} using {@link Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s
 * through referencing {@code null} from this ${lambda.type.simpleName}.
 *
 * @return An equal ${lambda.type.simpleName}, which ensures that its result is not {@code null}.
 */
@Nonnull
default ${lambda.name} ${genericTypeStringWithOptionalReturn} nonNull() {
    return (${parameterNameString}) -> Optional.ofNullable(${lambda.type.method}(${parameterNameString}));
}
</#macro>
<#-- @formatter:on -->
