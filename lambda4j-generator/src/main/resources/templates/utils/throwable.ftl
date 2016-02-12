<#-- prints the throwable declaration -->
<#macro printThrowableDeclaration target = lambda>
    <#compress>
        <#if (target.throwable)>throws Throwable</#if>
    </#compress>
</#macro>
