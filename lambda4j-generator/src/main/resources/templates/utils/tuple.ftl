<#-- @formatter:off -->
<#import "helpers.ftl" as helpers>
<#import "types.ftl" as types>

<#function printTuple target = lambda>
    <#local ret = "">
    <#-- lambda has arity 2 and first and second argument is not primitive or lambda third argument is primitive, then use Pair -->
    <#if ((target.arity == 2) && !helpers.isPrimitive(target.firstInputType) && !helpers.isPrimitive(target.secondInputType)) || helpers.isPrimitive(lambda.thirdInputType)>
        <#local ret = "Pair<" + target.firstInputType + ", " + target.secondInputType + ">">
    <#-- lambda has arity 3 and all input parameters are not primitive, then us Triple -->
    <#elseif (target.arity == 3) && !helpers.isPrimitive(target.firstInputType) && !helpers.isPrimitive(target.secondInputType) && !helpers.isPrimitive(target.thirdInputType)>
        <#local ret = "Triple<" + target.firstInputType + ", " + target.secondInputType + ", " + target.thirdInputType + ">">
    </#if>
    <#return ret>
</#function>

<#function printTupleAccess target = lambda>
    <#local ret = "">
    <#if (target.arity == 2) || helpers.isPrimitive(lambda.thirdInputType)>
        <#local ret = "tuple.getLeft(), tuple.getRight()">
    <#elseif (target.arity == 3)>
        <#local ret = "tuple.getLeft(), tuple.getMiddle(), tuple.getRight()">
    </#if>
    <#return ret>
</#function>
<#-- @formatter:on -->
