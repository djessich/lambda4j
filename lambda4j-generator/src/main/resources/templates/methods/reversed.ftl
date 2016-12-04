<#-- @formatter:off -->
<#import "../utils/filters.ftl" as filters>
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is not of type supplier or runnable (void input) and lambda does not get primitive primitive input -->
<#if !LambdaUtils.isOfTypeSupplier(lambda) && !LambdaUtils.isOfTypeRunnable(lambda) && !helpers.isPrimitiveLambdaInput(lambda)>
    <#-- build required reversed parameter strings -->
    <#assign genericParameterTypeStringReversed = .namespace.buildGenericParameterTypeStringReversed()>
    <#assign parameterNameStringReversed = .namespace.buildParameterNameStringReversed()>
    <#-- print reversed method -->
    <#if (lambda.arity >= 2)>
        <@.namespace.reversedMethod genericParameterTypeStringReversed parameterNameStringReversed/>
    <#else>
        <@.namespace.reversedMethodArityOne/>
    </#if>
</#if>

<#-- a helper macro to centralize reversed method and to avoid unnecessary indenting -->
<#macro reversedMethod genericParameterTypeStringReversed parameterNameStringReversed>
/**
 * Returns a reversed version of this ${lambda.type.simpleName}. This may be useful in recursive context.
 *
 * @return A reversed version of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeStringReversed} reversed() {
    return (${parameterNameStringReversed}) -> ${lambda.method}(${parameterNameString});
}
</#macro>

<#-- a helper macro to centralize reversed method for lambdas with arity one and to avoid unnecessary indenting -->
<#macro reversedMethodArityOne>
/**
 * Returns a reversed version of this ${lambda.type.simpleName}. This may be useful in recursive context.
 *
 * @return A reversed version of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeString} reversed() {
    return this;
}
</#macro>

<#-- a helper function which builds a string from all generic parameters in reversed order -->
<#function buildGenericParameterTypeStringReversed>
    <#local genericParameterTypeStringReversed = "">
    <#-- create a list which includes all input types in reversed order -->
    <#local parameters = ([lambda.firstInputType!"", lambda.secondInputType!"", lambda.thirdInputType!""])?reverse>
    <#local parameters = filters.filterEmpties(parameters)>
    <#-- create list made of reversed input types list and the return type -->
    <#local parametersWithReturn = parameters + [lambda.returnType!"", lambda.throwableType!""]>
    <#local parametersWithReturn = filters.filterEmpties(parametersWithReturn)>
    <#local parametersWithReturn = filters.filterPrimitives(parametersWithReturn)>
    <#-- create reversed generic parameter type string only if list has content -->
    <#if (parametersWithReturn?has_content)>
        <#local genericParameterTypeStringReversed = "<" + parametersWithReturn?join(", ", "", ">")>
    </#if>
    <#return genericParameterTypeStringReversed>
</#function>

<#-- a helper function which builds a string representing all names of lambda parameters in reversed order -->
<#function buildParameterNameStringReversed>
    <#local parameterNameStringReversed = "">
    <#-- create a list which includes all input types in reversed order -->
    <#local parameters = ([lambda.firstInputType!"", lambda.secondInputType!"", lambda.thirdInputType!""])?reverse>
    <#local parameters = filters.filterEmpties(parameters)>
    <#-- create list made of reversed input types list and the return type -->
    <#local parametersWithReturn = parameters + [lambda.returnType!""]>
    <#local parametersWithReturn = filters.filterEmpties(parametersWithReturn)>
    <#local parametersWithReturn = filters.filterPrimitives(parametersWithReturn)>
    <#-- create parameter name string only if list has content -->
    <#if (parameters?has_content)>
        <#list parameters as parameter>
            <#local parameterNameStringReversed = parameterNameStringReversed + types.buildParameterName(parameter)>
            <#if parameter?has_next>
                <#local parameterNameStringReversed = parameterNameStringReversed + ", ">
            </#if>
        </#list>
    </#if>
    <#return parameterNameStringReversed>
</#function>
<#-- @formatter:on -->
