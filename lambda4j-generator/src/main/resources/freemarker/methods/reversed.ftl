<#-- @formatter:off -->
<#import "../utils/filters.ftl" as filters>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is not primitive -->
<#if !lambda.primitive>
    <#assign parameters = [lambda.inputOneType!"", lambda.inputTwoType!"", lambda.inputThreeType!""]?reverse>
    <#assign parameters = parameters + [lambda.returnType]>
    <#if (parameters?size != 0)>
        <#assign genericTypeStringReversed = types.buildGenericParameterTypeString(lambda, parameters[0], parameters[1], parameters[2], parameters[3])>
        <#assign genericNameStringReversed = types.buildParameterNameString(lambda, parameters[0], parameters[1], parameters[2], parameters[3])>
    </#if>
    <@reversedMethod genericTypeStringReversed!"" genericNameStringReversed!""/>
</#if>

<#-- a helper macro to centralize reversed method and to avoid unnecessary indenting -->
<#macro reversedMethod genericTypeStringReversed = "" genericNameStringReversed = "">
/**
 * Returns a reversed version of this ${lambda.type.simpleName}. This may be useful in recursive context.
 *
 * @return A reversed version of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${lambda.name}${genericTypeStringReversed} reversed() {
    return (${genericNameStringReversed}) -> ${lambda.type.method}(${parameterNameString});
}
</#macro>
<#-- @formatter:on -->
