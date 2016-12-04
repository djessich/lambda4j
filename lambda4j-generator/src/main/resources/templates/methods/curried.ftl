<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda arity greater than 2, lambda is not primitive and lambda needs to return -->
<#if (lambda.arity >= 1) && !helpers.isPrimitiveLambda(lambda) && lambda.returnType?has_content>
    <#-- search for correct output lambda of curried method, which depends on global lambdas return type -->
    <#assign outputLambda = LambdaUtils.searchByReturnType(lambda.type, 1, lambda.returnType, lambda.throwable, false)>
    <#-- if lambda is throwable, then append throwable type -->
    <#if outputLambda.throwable>
        <#assign throwableString = ", ${lambda.throwableType}"/>
    </#if>
    <#-- if lambda has larity greater than or equal 3, generate variables for curried method -->
    <#if (lambda.arity >= 3)>
        <#assign lastStep = "${outputLambda.name}<${lambda.thirdInputType},">
        <#assign lastClosingTag = ">">
        <#assign lastArrow = "-> " + types.buildParameterName(lambda.thirdInputType)>
        <#assign lastThrowableString = throwableString!"">
    </#if>
    <#-- print curried method -->
    <#if (lambda.arity >= 2)>
        <@.namespace.curriedMethod throwableString!"" lastStep!"" lastClosingTag!"" lastArrow!"" lastThrowableString!""/>
    <#else>
        <@.namespace.curriedMethodArityOne/>
    </#if>
</#if>

<#-- a helper macro to centralize curried method and to avoid unnecessary indenting -->
<#macro curriedMethod throwableString = "" lastStep = "" lastClosingTag = "" lastArrow = "" lastThrowableString = "">
/**
 * Returns a curried version of this ${lambda.type.simpleName}.
 *
 * @return A curried version of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${outputLambda.name}<${lambda.firstInputType}, ${outputLambda.name}<${lambda.secondInputType}, ${lastStep} ${lambda.returnType} ${lastThrowableString} ${lastClosingTag} ${throwableString}> ${throwableString}> curried() {
    return t -> u ${lastArrow} -> ${lambda.method}(${parameterNameString});
}
</#macro>

<#-- a helper macro to centralize curried method for arity one lambdas and to avoid unnecessary indenting -->
<#macro curriedMethodArityOne>
/**
 * Returns a curried version of this ${lambda.type.simpleName}.
 *
 * @return A curried version of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${outputLambda.name}${genericParameterTypeString} curried() {
    return this;
}
</#macro>
<#-- @formatter:on -->
