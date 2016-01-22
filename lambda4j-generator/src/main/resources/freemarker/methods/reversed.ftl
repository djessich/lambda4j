<#-- @formatter:off -->
<#import "../utils/filters.ftl" as filters>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is not primitive -->
<#if !lambda.primitive>
    <#-- create a list which includes all input types in reversed order -->
    <#assign parameters = ([lambda.inputOneType!""] + [lambda.inputTwoType!""] + [lambda.inputThreeType!""])?reverse>
    <#assign parameters = filters.filterEmpties(parameters)>

    <#-- create list made of reversed input types list and the return type -->
    <#assign parametersWithReturn = parameters + [lambda.returnType!""]>
    <#assign parametersWithReturn = filters.filterEmpties(parametersWithReturn)>
    <#assign parametersWithReturn = filters.filterPrimitives(parametersWithReturn)>

    <#-- create parameter name string only if list has content -->
    <#if (parameters?has_content)>
        <#assign parameterNameStringReversed = "">
        <#list parameters as parameter>
            <#assign parameterNameStringReversed = parameterNameStringReversed + types.buildParameterName(parameter)>
            <#if parameter?has_next>
                <#assign parameterNameStringReversed = parameterNameStringReversed + ", ">
            </#if>
        </#list>
    </#if>

    <#-- create reversed generic parameter type string only if list has content -->
    <#if (parametersWithReturn?has_content)>
        <#assign genericParameterTypeStringReversed = "<" + parametersWithReturn?join(", ", "", ">")>
    </#if>
    <@reversedMethod genericParameterTypeStringReversed!"" parameterNameStringReversed!""/>
</#if>

<#-- a helper macro to centralize reversed method and to avoid unnecessary indenting -->
<#macro reversedMethod genericParameterTypeStringReversed = "" parameterNameStringReversed = "">
/**
 * Returns a reversed version of this ${lambda.type.simpleName}. This may be useful in recursive context.
 *
 * @return A reversed version of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeStringReversed} reversed() {
    return (${parameterNameStringReversed}) -> ${lambda.type.method}(${parameterNameString});
}
</#macro>
<#-- @formatter:on -->
