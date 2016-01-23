<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>

<#-- parse only if lambda is of type Consumer -->
<#if !LambdaUtils.isOfTypeConsumer(lambda)>
     <@.namespace.constantMethod/>
</#if>

<#-- a helper macro to centralize constant method and to avoid unnecessary indenting -->
<#macro constantMethod>
/**
 * Creates a {@link ${lambda.name}} which always returns a given value.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param r The return value for the constant
 * @return A {@code ${lambda.name}} which always returns a given value.
 */
${annotation.nonnull}
static ${genericParameterTypeString} ${lambda.name}${genericParameterTypeString} constant(${types.buildParameter(lambda.returnType)}) {
    return (${parameterNameString}) -> ${types.buildParameterName(lambda.returnType)};
}
</#macro>
<#-- @formatter:on -->