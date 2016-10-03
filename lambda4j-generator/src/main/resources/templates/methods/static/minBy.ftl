<#-- @formatter:off -->

<#import "../../utils/types.ftl" as types>

<#-- parse only if lambda is of type operator with arity of 2 -->
<#if (lambda.arity == 2) && LambdaUtils.isOfTypeOperator(lambda)>
    <@.namespace.maxByMethod/>
</#if>

<#-- a helper macro to centralize maxBy method and to avoid unnecessary indenting -->
<#macro maxByMethod>
/**
* Returns a {@link ${lambda.name}} which returns the lesser of two elements according to the specified {@code Comparator}.
*
<#include "../../javadoc/paramGenericInput.ftl">
* @param comparator A {@code Comparator} for comparing the two values
* @return A {@code ${lambda.name}} which returns the lesser of its operands, according to the supplied {@code Comparator}.
<#include "../../javadoc/throwsNullPointerException.ftl">
* @see BinaryOperator#minBy(Comparator)
*/
${annotation.nonnull}
static ${genericParameterTypeStringWithThrowableErasure} ${lambda.name}${genericParameterTypeString} minBy(${annotation.nonnull} final Comparator<${types.buildGenericParameterType(lambda.returnType)}> comparator) {
    Objects.requireNonNull(comparator);
    return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
}
</#macro>
<#-- @formatter:on -->