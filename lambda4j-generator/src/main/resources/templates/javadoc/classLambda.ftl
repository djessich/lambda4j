<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>

<#include "./classConsumer.ftl">
<#include "./classFunction.ftl">
<#include "./classPredicate.ftl">
<#include "./classSupplier.ftl">

<#-- a helper method to print a javadoc string which describes the lambda in words -->
<#function javadocDescriptionsString target = lambda>
    <#local ret = "">
    <#-- lambda is of arity one -->
    <#if (target.arity == 1)>
        <#local ret = "one ">
        <#-- lambda has primitive argument; otherwise do not append as it is generic lambda -->
        <#if helpers.isPrimitive(target.firstInputType)>
            <#-- generated: one {@code byte}-valued input argument -->
            <#local ret = ret + "{@code " + target.firstInputType.typeSimpleName?lower_case + "}-valued ">
        </#if>
        <#-- generated: one input argument -->
        <#local ret = ret + "input argument">

    <#-- lambda is of arity two -->
    <#elseif (target.arity == 2)>
        <#-- lambda has generic and primitive argument; generated: one object-valued and one {@code byte}-valued input argument -->
        <#if !helpers.isPrimitive(target.firstInputType) && helpers.isPrimitive(target.secondInputType)>
            <#local ret = "one " + target.firstInputType.typeSimpleName?lower_case + "-valued and one {@code " + target.secondInputType.typeSimpleName?lower_case + "}-valued input argument">

        <#-- lambda has either two generic or primtive arguments -->
        <#else>
            <#local ret = "two ">
            <#if helpers.isPrimitive(target.secondInputType)>
                <#-- generated: two {@code byte}-valued input arguments -->
                <#local ret = ret + "{@code " + target.secondInputType.typeSimpleName?lower_case + "}-valued ">
            </#if>
            <#-- generated: two input arguments -->
            <#local ret = ret + "input arguments">
        </#if>

    <#-- lamba is of arity three -->
    <#elseif (target.arity == 3)>
        <#-- lambda has two generic and primitive argument; generated: two object-valued and one {@code byte}-valued input argument -->
        <#if !helpers.isPrimitive(target.firstInputType) && !helpers.isPrimitive(target.secondInputType) && helpers.isPrimitive(target.thirdInputType)>
            <#local ret = "two " + target.firstInputType.typeSimpleName?lower_case + "-valued and one {@code " + target.thirdInputType.typeSimpleName?lower_case + "}-valued input argument">

        <#-- lambda has one generic and two primitive arguments; generated: one object-valued and two {@code byte}-valued input arguments -->
        <#elseif !helpers.isPrimitive(target.firstInputType) && helpers.isPrimitive(target.secondInputType) && helpers.isPrimitive(target.thirdInputType)>
            <#local ret = "one " + target.firstInputType.typeSimpleName?lower_case + "-valued and two {@code " + target.thirdInputType.typeSimpleName?lower_case + "}-valued input arguments">

        <#-- lambda has either three generic or primtive arguments -->
        <#else>
            <#local ret = "three ">
            <#if helpers.isPrimitive(target.thirdInputType)>
                <#-- generated: three {@code byte}-valued input arguments -->
                <#local ret = ret + "{@code " + target.thirdInputType.typeSimpleName?lower_case + "}-valued ">
            </#if>
            <#-- generated: three input arguments -->
            <#local ret = ret + "input arguments">
        </#if>
    </#if>
    <#return ret>
</#function>

<#-- a helper method which generates javadoc return string -->
<#function javadocReturnString target = lambda>
    <#local ret = "">
    <#if helpers.isPrimitive(target.returnType)>
        <#local ret = "{@code " + target.returnType.typeSimpleName?lower_case + "}-valued ">
    </#if>
    <#return ret>
</#function>

<#-- a helper method which generates a javadoc string for describing the lambdas specialization -->
<#function javadocSpecializationString target = lambda>
    <#local ret = "">
    <#-- lambda is of arity null and has primitive return -->
    <#if (target.arity == 0) && helpers.isPrimitive(target.returnType)>
        <#local ret = ret + "primitive">

    <#-- lambda is of arity one and has primitive argument; generated: primitive -->
    <#elseif (target.arity == 1) && (helpers.isPrimitive(target.firstInputType) || helpers.isPrimitive(target.returnType))>
        <#local ret = ret + "primitive">

    <#-- lambda is of arity two -->
    <#elseif (target.arity == 2)>
        <#-- lambda has generic and primitive argument; generated: (reference, byte) -->
        <#if !helpers.isPrimitive(target.firstInputType) && helpers.isPrimitive(target.secondInputType)>
            <#local ret = "(reference, " + target.secondInputType.typeSimpleName?lower_case + ")">

        <#-- lambda has two primtive arguments; generated: primitive -->
        <#elseif (helpers.isPrimitive(target.firstInputType) && helpers.isPrimitive(target.secondInputType)) || helpers.isPrimitive(target.returnType)>
            <#local ret = ret + "primitive">
        </#if>

    <#-- lamba is of arity three -->
    <#elseif (target.arity == 3)>
        <#-- lambda has two generic and primitive argument; generated: (reference, reference, byte) -->
        <#if !helpers.isPrimitive(target.firstInputType) && !helpers.isPrimitive(target.secondInputType) && helpers.isPrimitive(target.thirdInputType)>
            <#local ret = "(reference, reference, " + target.thirdInputType.typeSimpleName + ")">

        <#-- lambda has one generic and two primitive arguments; generated: (reference, byte, byte) -->
        <#elseif !helpers.isPrimitive(target.firstInputType) && helpers.isPrimitive(target.secondInputType) && helpers.isPrimitive(target.thirdInputType)>
            <#local ret = "(reference, " + target.secondInputType.typeSimpleName?lower_case + ", " + target.thirdInputType.typeSimpleName?lower_case + ")">

        <#-- lambda has three primtive arguments; generated: primitive -->
        <#elseif (helpers.isPrimitive(target.firstInputType) && helpers.isPrimitive(target.secondInputType) && helpers.isPrimitive(target.thirdInputType)) || helpers.isPrimitive(target.returnType)>
            <#local ret = ret + "primitive">
        </#if>
    </#if>
    <#return ret>
</#function>

<#-- a helper function which builds the lambdas method link used in javadoc -->
<#function javadocLambdaMethodLinkString target = lambda>
    <#local ret = target.method + "(">
    <#if (target.arity >= 1)>
        <#local ret = ret + lambda.firstInputType.typeSimpleName>
    </#if>
    <#if (target.arity >= 2)>
        <#local ret = ret + ", " + lambda.secondInputType.typeSimpleName>
    </#if>
    <#if (target.arity >= 3)>
        <#local ret = ret + ", " + lambda.thirdInputType.typeSimpleName>
    </#if>
    <#local ret = ret + ")">
    <#return ret>
</#function>

<#-- a helper method which prints the javadoc throwable string for throwable lambdas -->
<#function javadocThrowableString target = lambda>
    <#local ret = "">
    <#if target.throwable>
        <#local ret = " which is able to throw any {@link Throwable}">
    </#if>
    <#return ret>
</#function>
<#-- @formatter:on -->
