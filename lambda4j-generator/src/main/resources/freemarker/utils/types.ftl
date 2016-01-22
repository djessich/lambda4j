<#import "./filters.ftl" as filters>
<#import "./helpers.ftl" as helpers>

<#function buildParameter param>
    <#local genericString = "">
    <#if param??>
        <#local genericString = param + " " + buildParameterName(param)>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterName param>
    <#local genericString = "">
    <#if param??>
        <#if helpers.isPrimitive(param)>
            <#local genericString = genericString + "value">
        <#else>
            <#local genericString = genericString + param?lower_case>
        </#if>
    </#if>
    <#return genericString>
</#function>


<#function buildParameterString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.inputOneType!"", target.inputTwoType!"", target.inputThreeType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + buildParameter(type)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterNameString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.inputOneType!"", target.inputTwoType!"", target.inputThreeType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + buildParameterName(type)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>


<#function buildGenericParameterTypeString target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#local target = otherParametersToTarget(target, other1, other2, other3, other4)>
    <#local types = [target.inputOneType!"", target.inputTwoType!"", target.inputThreeType!"", target.returnType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<" + types?join(", ", "", ">")>
    </#if>
    <#return genericString>
</#function>

<#function buildGenericParameterTypeStringWithErasure target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#local target = otherParametersToTarget(target, other1, other2, other3, other4)>
    <#local types = [target.inputOneType!"", target.inputTwoType!"", target.inputThreeType!"", target.returnType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#if type == target.returnType!"">
                <#local genericString = genericString + "? extends " + type>
            <#else>
                <#local genericString = genericString + "? super " + type>
            </#if>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#function otherParametersToTarget target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#if other1?has_content>${target.setInputOneType(other1)}</#if>
    <#if other2?has_content>${target.setInputTwoType(other2)}</#if>
    <#if other3?has_content>${target.setInputThreeType(other3)}</#if>
    <#if other4?has_content>${target.setReturnType(other4)}</#if>
    <#return target>
</#function>