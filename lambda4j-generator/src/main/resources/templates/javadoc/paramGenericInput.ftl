<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- TODO exclusion for operators as they just will havee T as input and output -->
<#-- if lambdas arity is greater than 1 and first param is not primitive then print javadoc param -->
<#if (target.arity >= 1) && !helpers.isPrimitive(target.firstInputType)>
* @param <${target.firstInputType}> The type of the ${helpers.first()}argument to the ${target.type.simpleName}
</#if>
<#-- if lambdas arity is greater than 2 and second param is not primitive then print javadoc param -->
<#if (target.arity >= 2) && !helpers.isPrimitive(target.secondInputType)>
* @param <${target.secondInputType}> The type of the second argument to the ${target.type.simpleName}
</#if>
<#-- if lambdas arity is greater than 3 and third param is not primitive then print javadoc param -->
<#if (target.arity >= 3) && !helpers.isPrimitive(target.thirdInputType)>
* @param <${target.thirdInputType}> The type of the third argument to the ${target.type.simpleName}
</#if>
<#-- if lambda return is not primitive than print javadoc param -->
<#if !helpers.isPrimitive(target.returnType)>
* @param <${target.returnType}> The type of return value from the ${target.type.simpleName}
</#if>
<#-- @formatter:on -->
