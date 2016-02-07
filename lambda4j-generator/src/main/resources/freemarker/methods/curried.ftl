<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda arity greater than 2, lambda is not primitive and lambda needs to return -->
<#if (lambda.arity >= 2 && !helpers.isPrimitiveLambda(lambda) && lambda.returnType?has_content)>
    <#-- search for correct output lambda of curried method -->
    <#assign outputLambda = LambdaUtils.searchByReturnType(lambda.type, 1, lambda.returnType, lambda.throwable)>
    <#-- if lambda has larity greater than or equal 3, generate variables for curried method -->
    <#if (lambda.arity >= 3)>
        <#assign lastStep = "${outputLambda.name}<${lambda.thirdInputType},">
        <#assign lastClosingTag = ">">
        <#assign lastArrow = "-> " + types.buildParameterName(lambda.thirdInputType)>
    </#if>
    <#-- print curried method -->
    <@.namespace.curriedMethod lastStep!"" lastClosingTag!"" lastArrow!""/>
</#if>

<#-- a helper macro to centralize curried method and to avoid unnecessary indenting -->
<#macro curriedMethod lastStep = "" lastClosingTag = "" lastArrow = "">
/**
* Returns a curried version of this ${lambda.type.simpleName}.
*
* @return A curried version of this ${lambda.type.simpleName}.
*/
${annotation.nonnull}
default ${outputLambda.name}<${lambda.firstInputType}, ${outputLambda.name}<${lambda.secondInputType}, ${lastStep} ${lambda.returnType} ${lastClosingTag}>> curried() {
    return t -> u ${lastArrow} -> ${lambda.type.method}(${parameterNameString});
}
</#macro>
<#-- @formatter:on -->
