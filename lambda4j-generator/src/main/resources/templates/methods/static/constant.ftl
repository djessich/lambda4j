<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>

<#-- TODO Javadoc: generic operator has param for inputs and return type with same name (@param <T> ... 2x) -->
<#-- TODO UnaryOperator has return type name 't' so var 't' is defined twice in scope -->
<#-- TODO generic operator of arity greater than 2 has lambda inputs with same parameter name ( return (t, t) -> ... ) -->
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
static ${genericParameterTypeString} ${lambda.name}${genericParameterTypeString} constant(${types.buildParameter(lambda.returnType)}) {
    return (${parameterNameString}) -> ${types.buildParameterName(lambda.returnType)};
}
</#macro>
<#-- @formatter:on -->