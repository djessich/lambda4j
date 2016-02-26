<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- if lambdas arity is greater than 1 and first param is not primitive then print javadoc param -->
<#if (lambda.arity >= 1) && !helpers.isPrimitive(lambda.firstInputType)>
<#-- if lambda is a operator or a comparator-->
<#if LambdaUtils.isOfTypeOperator(lambda) || LambdaUtils.isOfTypeComparator(lambda)>
 * @param <${lambda.firstInputType}> The type of the argument${(lambda.arity > 1)?then("s", "")} to the ${lambda.type.simpleName} and of return from the ${lambda.type.simpleName}
<#else>
 * @param <${lambda.firstInputType}> The type of the ${helpers.first()}argument to the ${lambda.type.simpleName}
</#if>
</#if>
<#-- if lambdas arity is greater than 2, lambda is not of type operator or comparator, and second param is not primitive then print javadoc param -->
<#if (lambda.arity >= 2) && !LambdaUtils.isOfTypeOperator(lambda) && !LambdaUtils.isOfTypeComparator(lambda) && !helpers.isPrimitive(lambda.secondInputType)>
 * @param <${lambda.secondInputType}> The type of the second argument to the ${lambda.type.simpleName}
</#if>
<#-- if lambdas arity is greater than 3, lambda is not of type operator or comparator, and third param is not primitive then print javadoc param -->
<#if (lambda.arity >= 3) && !LambdaUtils.isOfTypeOperator(lambda) && !LambdaUtils.isOfTypeComparator(lambda) && !helpers.isPrimitive(lambda.thirdInputType)>
 * @param <${lambda.thirdInputType}> The type of the third argument to the ${lambda.type.simpleName}
</#if>
<#-- if lambda is not of type operator or comparator and return is not primitive than print javadoc param -->
<#if !LambdaUtils.isOfTypeOperator(lambda) && !LambdaUtils.isOfTypeComparator(lambda) && !helpers.isPrimitive(lambda.returnType)>
 * @param <${lambda.returnType}> The type of return value from the ${lambda.type.simpleName}
</#if>
<#-- @formatter:on -->
