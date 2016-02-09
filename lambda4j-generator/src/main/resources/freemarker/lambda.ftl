<#import "../../utils/types.ftl" as types>

<#global parameterString = types.buildParameterString()>
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

<#--<#assign groupid>at.gridtec.lambda4j</#assign>-->
<#assign groupid>target</#assign>

<#include "header/copyright.ftl">
package ${groupid}

<#include "header/imports.ftl">

@SuppressWarnings("unused")
@FunctionalInterface
public interface ${lambda.name}${genericParameterTypeString} {

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
<#include "methods/lambdaMethod.ftl">
<#include "methods/arity.ftl">
<#include "methods/compose.ftl">
<#--<#include "methods/composeToPrimitive.ftl">-->
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
<#include "methods/nonNull.ftl">
<#include "methods/boxed.ftl">
}
