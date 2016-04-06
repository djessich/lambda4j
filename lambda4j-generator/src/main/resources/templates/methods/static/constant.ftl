<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>

<#-- parse only if lambda is not of type Consumer or Runnable (no return) -->
<#if !LambdaUtils.isOfTypeConsumer(lambda) && !LambdaUtils.isOfTypeRunnable(lambda)>
     <@.namespace.constantMethod/>
</#if>

<#-- a helper macro to centralize constant method and to avoid unnecessary indenting -->
<#macro constantMethod>
/**
 * Creates a {@link ${lambda.name}} which always returns a given value.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param ${types.buildParameterName(lambda.returnType)} The return value for the constant
 * @return A {@code ${lambda.name}} which always returns a given value.
 */
${annotation.nonnull}
static ${genericParameterTypeStringWithThrowableErasure} ${lambda.name}${genericParameterTypeString} constant(${types.buildParameter(lambda.returnType)}) {
    return (${parameterNameString}) -> ${types.buildParameterName(lambda.returnType)};
}
</#macro>
<#-- @formatter:on -->