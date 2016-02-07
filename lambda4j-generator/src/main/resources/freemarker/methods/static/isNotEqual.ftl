<#-- @formatter:off -->

<#-- parse only if lambda is of type predicate -->
<#if LambdaUtils.isOfTypePredicate(lambda)>
    <@.namespace.isNotEqualMethod/>
</#if>

<#-- a helper macro to centralize isNotEqual method and to avoid unnecessary indenting -->
<#macro isNotEqualMethod>

</#macro>
<#-- @formatter:on -->