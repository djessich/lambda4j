<#import "./utils/helpers.ftl" as helpers>
<#import "./utils/types.ftl" as types>

<#global parameterString = types.buildParameterString()>
<#global parameterTypeString = types.buildParameterTypeString()>
<#global parameterSimpleTypeString = types.buildParameterSimpleTypeString()>
<#global parameterNameString = types.buildParameterNameString()>
<#global genericParameterTypeString = types.buildGenericParameterTypeString()>
<#global genericParameterTypeStringWithErasure = types.buildGenericParameterTypeStringWithErasure()>

<#global Object = LambdaUtils.getObjectTypeEntity()>
<#global boolean = LambdaUtils.getBooleanTypeEntity()>
<#global byte = LambdaUtils.getByteTypeEntity()>
<#global char = LambdaUtils.getCharTypeEntity()>
<#global double = LambdaUtils.getDoubleTypeEntity()>
<#global float = LambdaUtils.getFloatTypeEntity()>
<#global int = LambdaUtils.getIntTypeEntity()>
<#global long = LambdaUtils.getLongTypeEntity()>
<#global short = LambdaUtils.getShortTypeEntity()>
<#global void = LambdaUtils.getVoidTypeEntity()>

<#-- Prepare extends string -->
<#assign extends = "">

<#-- If lambda is a generic operator, then append to extends string -->
<#assign isGenericOperator = (LambdaUtils.isOfTypeOperator(lambda) && !helpers.isPrimitive(lambda.returnType))>
<#if isGenericOperator>
    <#assign extenderLambda = LambdaUtils.searchByInputTypesAndReturnType(LambdaUtils.getFunctionType(), lambda.arity, Object, Object, Object, Object, lambda.throwable, false)>
    <#assign returnTypeName = lambda.returnType.typeName>
    <#assign extends += ", " + extenderLambda.name + types.buildGenericParameterTypeString(extenderLambda, returnTypeName, returnTypeName, returnTypeName, returnTypeName)>
</#if>

<#-- If lambda is a JDK lambda, then append to extends string -->
<#if lambda.fromJDK>
    <#assign extenderLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, false, true)>
    <#assign extends += ", " + extenderLambda.name + types.buildGenericParameterTypeString(extenderLambda)>
</#if>

<#include "header/copyright.ftl">
package ${lambda.packageName};

<#include "header/imports.ftl">

<#compress>
    <#include "javadoc/classLambda.ftl">
</#compress>

@SuppressWarnings("unused")
@FunctionalInterface
public interface ${lambda.name}${genericParameterTypeString} extends Lambda ${extends} {

<#include "methods/static/of.ftl">
<#include "methods/static/call.ftl">
<#include "methods/static/only.ftl">
<#include "methods/static/identity.ftl">
<#include "methods/static/constant.ftl">
<#include "methods/static/alwaysTrue.ftl">
<#include "methods/static/alwaysFalse.ftl">
<#include "methods/static/isEqual.ftl">
<#--&lt;#&ndash;<#include "methods/static/min.ftl">&ndash;&gt;-->
<#--&lt;#&ndash;<#include "methods/static/max.ftl">&ndash;&gt;-->
<#--&lt;#&ndash;<#include "methods/static/minBy.ftl">&ndash;&gt;-->
<#--&lt;#&ndash;<#include "methods/static/maxBy.ftl">&ndash;&gt;-->

<#-- if lambda is not a generic operator, include other methods (as generic operators will extend from their function codomain -->
<#if !isGenericOperator>
    <#include "methods/lambdaMethod.ftl">
    <#include "methods/arity.ftl">
    <#include "methods/compose.ftl">
    <#include "methods/composeFromPrimitive.ftl">
    <#include "methods/andThen.ftl">
    <#include "methods/andThenToPrimitive.ftl">
    <#include "methods/consume.ftl">
    <#include "methods/negate.ftl">
    <#include "methods/and.ftl">
    <#include "methods/or.ftl">
    <#include "methods/xor.ftl">
<#--<#include "methods/partial.ftl">-->
    <#include "methods/curried.ftl">
    <#include "methods/tupled.ftl">
    <#include "methods/reversed.ftl">
    <#include "methods/memoized.ftl">
    <#include "methods/nonNull.ftl">
    <#include "methods/boxed.ftl">
</#if>
}
