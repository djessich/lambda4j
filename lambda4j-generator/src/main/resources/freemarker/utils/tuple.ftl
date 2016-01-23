<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#function printTuple target = lambda>
    <#local ret = "">
    <#local isInputOnePrimitive = helpers.isPrimitive(target.inputOneType!"")>
    <#local isInputTwoPrimitive = helpers.isPrimitive(target.inputTwoType!"")>
    <#local isInputThreePrimitive = helpers.isPrimitive(target.inputThreeType!"")>
    <#if !isInputOnePrimitive && !isInputTwoPrimitive>
        <#local ret = "Pair<" + target.inputOneType + ", " + target.inputTwoType + ">">
    <#elseif !isInputOnePrimitive && !isInputTwoPrimitive && !isInputThreePrimitive>
        <#local ret = "Triple<" + target.inputOneType + ", " + target.inputTwoType + ", " + target.inputThreeType + ">">
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
