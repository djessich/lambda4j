<#import "../../utils/types.ftl" as types>

<#global parameterString = types.buildParameterString()>
<#global parameterNameString = types.buildParameterNameString()>
<#global genericParameterTypeString = types.buildGenericParameterTypeString()>
<#global genericParameterTypeStringWithErasure = types.buildGenericParameterTypeStringWithErasure()>

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
<#include "methods/static/constant.ftl">

<#include "methods/lambdaMethod.ftl">
<#include "methods/arity.ftl">
<#include "methods/curried.ftl">
<#include "methods/tupled.ftl">
<#include "methods/reversed.ftl">
<#include "methods/nonNull.ftl">

}
