<#-- @formatter:off -->
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda arity greater than 2, lambda is not primitive and lambda needs to return -->
<#if (lambda.arity >= 2 && !lambda.primitive && lambda.returnType??)>
    <#-- search for correct output lambda of curried method -->
    <#assign outputLambda = LambdaUtils.search(lambda.type, 1, lambda.primitive, lambda.throwable)>
    <#-- if lambda has larity greater than or equal 3, generate variables for curried method -->
    <#if (lambda.arity >= 3)>
        <#assign lastStep = "${outputLambda.name}<${lambda.inputThreeType},">
        <#assign lastClosingTag = ">">
        <#assign lastArrow = "-> " + types.buildParameterName(lambda.inputThreeType)>
    </#if>
    <#-- print curried method -->
    <@curriedMethod lastStep!"" lastClosingTag!"" lastArrow!""/>
</#if>

<#-- a helper macro to centralize curried method and to avoid unnecessary indenting -->
<#macro curriedMethod lastStep = "" lastClosingTag = "" lastArrow = "">
/**
* Returns a curried version of this ${lambda.type.simpleName}.
*
* @return A curried version of this ${lambda.type.simpleName}.
*/
${annotation.nonnull}
default ${outputLambda.name}<${lambda.inputOneType}, ${outputLambda.name}<${lambda.inputTwoType}, ${lastStep} ${lambda.returnType} ${lastClosingTag}>> curried() {
    return t -> u ${lastArrow} -> ${lambda.type.method}(${parameterNameString});
}
</#macro>

<#-- helper macros to use in cases when lambda arity is greater than 3 -->
<#function curriedMethodPrintStep>
    <#if (lambda.arity >= 3)>
        <#return "${outputLambda.name}<${lambda.inputThreeType},">
    </#if>
    <#return "">
</#function>
<#function curriedMethodPrintClosingTag>
    <#if (lambda.arity >= 3)>
        <#return ">">
    </#if>
    <#return "">
</#function>
<#function curriedMethodPrintArrow>
    <#if (lambda.arity >= 3)>
        <#return "-> " + types.buildParameterName(lambda.inputThreeType)>
    </#if>
    <#return "">
</#function>
<#-- @formatter:on -->
