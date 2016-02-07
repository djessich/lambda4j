<#-- @formatter:off -->

<#-- if lambdas arity is equal to 2, then print see tag -->
<#if (lambda.arity == 2)>
* @see org.apache.commons.lang3.tuple.Pair
<#-- if lambdas arity is equal to 3, then print see tag -->
<#elseif (lambda.arity == 3)>
* @see org.apache.commons.lang3.tuple.Triple
</#if>
<#-- @formatter:on -->