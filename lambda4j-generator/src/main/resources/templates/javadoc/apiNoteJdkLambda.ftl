<#-- @formatter:off -->
<#if lambda.fromJDK && lambda.throwable>
 * @apiNote This is a throwable JDK lambda.
<#elseif lambda.fromJDK>
 * @apiNote This is a JDK lambda.
</#if>
<#-- @formatter:on -->