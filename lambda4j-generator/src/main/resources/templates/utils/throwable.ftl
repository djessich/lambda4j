<#-- prints the throwable declaration -->
<#macro printThrowableDeclaration target = lambda>
    <#compress>
        <#if (target.throwable)>throws ${target.throwableType.typeName}</#if>
    </#compress>
</#macro>
