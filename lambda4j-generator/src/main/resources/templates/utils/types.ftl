<#import "filters.ftl" as filters>
<#import "throwable.ftl" as throwable>
<#import "helpers.ftl" as helpers>

<#-- ##### parameter functions START ##### -->

<#function buildParameter param target = lambda>
    <#local genericString = "">
    <#if param?has_content>
        <#local genericString = .namespace.buildParameterType(param, target) + " " + .namespace.buildParameterName(param, target)>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterType param target = lambda>
    <#local genericString = "">
    <#if param?has_content>
        <#local genericString = genericString + param.typeName>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterSimpleType param target = lambda>
    <#local genericString = "">
    <#if param?has_content>
        <#local genericString = genericString + param.typeSimpleName>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterName param target = lambda>
    <#local genericString = "">
    <#if param?has_content>
        <#local genericString = genericString + param.name?lower_case>
        <#if param.primitive && (param.count > 0)>
            <#local count = 0>
            <#if (target.firstInputType?has_content)>
                <#local count = target.firstInputType.primitive?then(count, count + 1)>
            </#if>
            <#if (target.secondInputType?has_content)>
                <#local count = target.secondInputType.primitive?then(count, count + 1)>
            </#if>
            <#local genericString = genericString + ((target.arity - count) > 1)?then((param.count), "")>
        </#if>
    </#if>
    <#return genericString>
</#function>

<#function buildGenericParameterType param target = lambda>
    <#local genericString = "">
    <#if param?has_content>
        <#local classMap = { "char":"Character", "int":"Integer" }>
        <#local typeName = classMap[param.typeSimpleName]!"">
        <#if typeName?has_content>
            <#local genericString = genericString + typeName?cap_first>
        <#else>
            <#local genericString = genericString + param.typeName?cap_first>
        </#if>
    </#if>
    <#return genericString>
</#function>

<#-- ##### parameter functions END ##### -->

<#-- ##### parameter string functions START ##### -->

<#function buildParameterString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameter(type, target)>
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
            <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterSimpleTypeString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterSimpleType(type, target)>
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
            <#local genericString = genericString + .namespace.buildParameterName(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>

<#-- ##### parameter string functions END ##### -->

<#-- ##### generic parameter string functions START ##### -->

<#function buildGenericParameterTypeString target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3, other4)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!"", target.returnType!""]>
    </#if>
    <#local types = types + [target.throwableType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#function buildGenericParameterTypeStringWithoutThrowable target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3, other4)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!"", target.returnType!""]>
    </#if>
    <#local types = types + [target.throwableType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#function buildGenericParameterTypeStringWithThrowableErasure target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3, other4)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!"", target.returnType!""]>
    </#if>
    <#local types = types + [target.throwableType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#if type == target.throwableType!"">
                <#local genericString = genericString + .namespace.buildParameterType(type, target) + " extends Throwable">
            <#else>
                <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            </#if>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
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
    <#local types = types + [target.throwableType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#if type == target.throwableType!"">
                <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            <#elseif type == target.returnType!"">
                <#if LambdaUtils.isOfTypeOperator(target)>
                    <#local genericString = genericString + .namespace.buildParameterType(type, target)>
                <#else>
                    <#local genericString = genericString + "? extends " + .namespace.buildParameterType(type, target)>
                </#if>
            <#else>
                <#local genericString = genericString + "? super " + .namespace.buildParameterType(type, target)>
            </#if>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#function buildGenericInputParameterTypeString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    </#if>
    <#local types = types + [target.throwableType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#function buildGenericInputParameterTypeStringWithoutThrowable target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    </#if>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#function buildGenericInputParameterTypeStringWithThrowableErasure target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    </#if>
    <#local types = types + [target.throwableType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#if type == target.throwableType!"">
                <#local genericString = genericString + .namespace.buildParameterType(type, target) + " extends Throwable">
            <#else>
                <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            </#if>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#-- ##### generic parameter string functions END ##### -->

<#-- ##### helpers functions START ##### -->

<#function otherParametersToTarget target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#assign copy = LambdaUtils.copy(target)> <#-- copy lambda as we will change its input arguments -->
    <#if other1?has_content && copy.getFirstInputType()?has_content>
    ${copy.getFirstInputType().setTypeName(other1)}
    ${copy.getFirstInputType().setName(other1?lower_case)}
    </#if>
    <#if other2?has_content && copy.getSecondInputType()?has_content>
    ${copy.getSecondInputType().setTypeName(other2)}
    ${copy.getSecondInputType().setName(other2?lower_case)}
    </#if>
    <#if other3?has_content && copy.getThirdInputType()?has_content>
    ${copy.getThirdInputType().setTypeName(other3)}
    ${copy.getThirdInputType().setName(other3?lower_case)}
    </#if>
    <#if other4?has_content && copy.getReturnType()?has_content>
    ${copy.getReturnType().setTypeName(other4)}
    ${copy.getReturnType().setName(other4)}
    </#if>
    <#return copy>
</#function>

<#-- ##### helpers functions END ##### -->