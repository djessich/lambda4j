<#import "helpers.ftl" as helpers>
<#import "types.ftl" as types>

<#function printTuple target = lambda>
    <#local ret = "">
    <#local isInputOnePrimitive = helpers.isPrimitive(target.firstInputType!"")>
    <#local isInputTwoPrimitive = helpers.isPrimitive(target.secondInputType!"")>
    <#local isInputThreePrimitive = helpers.isPrimitive(target.thirdInputType!"")>
    <#if (target.arity == 2) && !isInputOnePrimitive && !isInputTwoPrimitive>
        <#local ret = "Pair<" + target.firstInputType + ", " + target.secondInputType + ">">
    <#elseif (target.arity == 3) && !isInputOnePrimitive && !isInputTwoPrimitive && !isInputThreePrimitive>
        <#local ret = "Triple<" + target.firstInputType + ", " + target.secondInputType + ", " + target.thirdInputType + ">">
    </#if>
    <#return ret>
</#function>

<#function printTupleAccess target = lambda>
    <#local ret = "">
    <#if (target.arity == 2)>
        <#local ret = "tuple.getLeft(), tuple.getRight()">
    <#elseif (target.arity == 3)>
        <#local ret = "tuple.getLeft(), tuple.getMiddle(), tuple.getRight()">
    </#if>
    <#return ret>
</#function>
