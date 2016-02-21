<#import "filters.ftl" as filters>
<#import "helpers.ftl" as helpers>

<#function buildParameter param>
    <#local genericString = "">
    <#if param?has_content>
        <#local genericString = .namespace.buildParameterType(param) + " " + .namespace.buildParameterName(param)>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterType param>
    <#local genericString = "">
    <#if param?has_content>
        <#local genericString = genericString + param.typeName>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterName param>
    <#local genericString = "">
    <#if param?has_content>
        <#if param.typePrimitive>
            <#local genericString = genericString + "value">
        <#else>
            <#local genericString = genericString + param.typeName?lower_case>
        </#if>
    </#if>
    <#return genericString>
</#function>


<#function buildParameterString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameter(type)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterTypeString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterType(type)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterNameString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterName(type)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>


<#function buildGenericParameterTypeString target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3, other4)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!"", target.returnType!""]>
    </#if>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<" + types?join(", ", "", ">")>
    </#if>
    <#return genericString>
</#function>

<#function buildGenericParameterTypeStringWithErasure target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3, other4)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!"", target.returnType!""]>
    </#if>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#if type == target.returnType!"">
                <#local genericString = genericString + "? extends " + .namespace.buildParameterType(type)>
            <#else>
                <#local genericString = genericString + "? super " + .namespace.buildParameterType(type)>
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
    <#assign copy = LambdaUtils.copy(target)> <#-- copy lambda as we will change its input arguments -->
    <#if other1?has_content && copy.getFirstInputType()?has_content>${copy.getFirstInputType().setTypeName(other1)}</#if>
    <#if other2?has_content && copy.getSecondInputType()?has_content>${copy.getSecondInputType().setTypeName(other2)}</#if>
    <#if other3?has_content && copy.getThirdInputType()?has_content>${copy.getThirdInputType().setTypeName(other3)}</#if>
    <#if other4?has_content && copy.getReturnType()?has_content>${copy.getReturnType().setTypeName(other4)}</#if>
    <#return copy>
</#function>