<#import "./helpers.ftl" as helpers>

<#-- filters all empty objects of the given list -->
<#function filterEmpties list = []>
    <#local filtered = []>
    <#list list as elem>
        <#if elem?has_content>
            <#local filtered = filtered + [elem]>
        </#if>
    </#list>
    <#return filtered>
</#function>

<#-- filters all primitive elements in the list -->
<#function filterPrimitives list = []>
    <#local filtered = []>
    <#list list as elem>
        <#if elem?has_content && !helpers.isPrimitive(elem)>
            <#local filtered = filtered + [elem]>
        </#if>
    </#list>
    <#return filtered>
</#function>
