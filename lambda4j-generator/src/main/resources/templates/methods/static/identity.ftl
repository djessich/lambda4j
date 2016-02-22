<#-- @formatter:off -->
<#import "../../utils/filters.ftl" as filters>
<#import "../../utils/types.ftl" as types>

<#-- parse only if lambda has arity 1 and return type -->
<#if lambda.arity == 1 && lambda.returnType?? && lambda.firstInputType?? && (lambda.returnType.equals(lambda.firstInputType))>
    <@.namespace.identityMethod/>
</#if>

<#-- a helper macro to centralize identity method and to avoid unnecessary indenting -->
<#macro identityMethod>
/**
 * Returns a {@link ${lambda.name}} that always returns its input argument.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @return A {@code  ${lambda.name}} that always returns its input argument
 */
${annotation.nonnull}
static ${types.buildGenericInputParameterTypeString()} ${lambda.name}${types.buildGenericParameterTypeString(lambda, lambda.firstInputType, "", "", lambda.firstInputType)} identity() {
    return (${parameterNameString}) -> ${parameterNameString};
}
</#macro>

<#-- TODO Remove if tested -->
<#--&lt;#&ndash; a helper function to build a generic input lambda string for boxed operation &ndash;&gt;-->
<#--<#function buildGenericInputTypeString target = lambda>-->
    <#--<#local parameters = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>-->
    <#--<#if LambdaUtils.isOfTypeOperator(target)>-->
        <#--<#local parameters = [target.returnType!""]>-->
    <#--</#if>-->
    <#--<#local parameters = filters.filterEmpties(parameters)>-->
    <#--<#local parameters = filters.filterPrimitives(parameters)>-->
    <#--<#local genericString = "">-->
    <#--<#if (parameters?has_content)>-->
        <#--<#local genericString = genericString + "<">-->
        <#--<#list parameters as parameter>-->
            <#--<#local genericString = genericString + types.buildParameterType(parameter, target)>-->
            <#--<#if parameter?has_next>-->
                <#--<#local genericString = genericString + ", ">-->
            <#--</#if>-->
        <#--</#list>-->
        <#--<#local genericString = genericString + ">">-->
    <#--</#if>-->
    <#--<#return genericString>-->
<#--</#function>-->
<#-- @formatter:on -->