<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- TODO exclusion for operators as they just will havee T as input and output -->
<#-- if lambdas arity is greater than 1 and first param is not primitive then print javadoc param -->
<#if (lambda.arity >= 1) && !helpers.isPrimitive(lambda.firstInputType)>
* @param <${lambda.firstInputType}> The type of the ${helpers.first()}argument to the ${lambda.type.simpleName}
</#if>
<#-- if lambdas arity is greater than 2 and second param is not primitive then print javadoc param -->
<#if (lambda.arity >= 2) && !helpers.isPrimitive(lambda.secondInputType)>
* @param <${lambda.secondInputType}> The type of the second argument to the ${lambda.type.simpleName}
</#if>
<#-- if lambdas arity is greater than 3 and third param is not primitive then print javadoc param -->
<#if (lambda.arity >= 3) && !helpers.isPrimitive(lambda.thirdInputType)>
* @param <${lambda.thirdInputType}> The type of the third argument to the ${lambda.type.simpleName}
</#if>
<#-- if lambda return is not primitive than print javadoc param -->
<#if !helpers.isPrimitive(lambda.returnType)>
* @param <${lambda.returnType}> The type of return value from the ${lambda.type.simpleName}
</#if>
<#-- @formatter:on -->
