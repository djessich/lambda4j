<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- if lambdas arity is greater than 1 then write javadoc param -->
<#if (lambda.arity >= 1)>
 * @param ${types.buildParameterName(lambda.inputOneType)} The ${helpers.first()} argument to the ${lambda.type.simpleName}
</#if>
<#-- if lambdas arity is greater than 2 then write javadoc param -->
<#if (lambda.arity >= 2)>
 * @param ${types.buildParameterName(lambda.inputTwoType)} The second argument to the ${lambda.type.simpleName}
</#if>
<#-- if lambdas arity is greater than 3 then write javadoc param -->
<#if (lambda.arity >= 3)>
 * @param ${types.buildParameterName(lambda.inputThreeType)} The third argument to the ${lambda.type.simpleName}
</#if>
<#-- @formatter:on -->
