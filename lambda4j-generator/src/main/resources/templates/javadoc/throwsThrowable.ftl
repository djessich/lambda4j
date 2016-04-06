<#-- @formatter:off -->

<#-- if lambda is throwable, then print -->
<#if lambda.throwable>
 * @throws ${lambda.throwableType.typeName} Any throwable from this ${lambda.type.simpleName}s action
</#if>
<#-- @formatter:on -->