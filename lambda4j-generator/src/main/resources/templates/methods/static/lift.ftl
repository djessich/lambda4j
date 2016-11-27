<#-- @formatter:off -->
<#import "../../utils/types.ftl" as types>
<#import "../../utils/helpers.ftl" as helpers>

<#-- parse only if lambda has a generic return (required for wrapping in optional<T>) and is no operator -->
<#if !helpers.isPrimitive(lambda.returnType) && !LambdaUtils.isOfTypeOperator(lambda)>
    <#-- search for correct input lambda which represents the same lambda as this one, but may be from jdk if such one exists -->
    <#assign inputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, lambda.throwable, true)>
    <#-- print lift method -->
    <@.namespace.liftMethod inputLambda />
</#if>

<#-- a helper macro to centralize lift method and to avoid unnecessary indenting -->
<#macro liftMethod inputLambda>
/**
 * Lifts a partial {@link ${inputLambda.name}} into a total {@link ${lambda.name}} that returns an {@link Optional} result.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param partial A function that is only defined for some values in its domain
 * @return A partial {@code ${inputLambda.name}} lifted into a total {@code ${lambda.name}} that returns an {@code Optional} result.
<#include "../../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
static ${genericParameterTypeStringWithThrowableErasure} ${lambda.name}${types.buildGenericParameterTypeString(lambda, "", "", "", "Optional<${lambda.returnType}>")} lift(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda)} partial) {
    Objects.requireNonNull(partial);
    return (${parameterNameString}) -> Optional.ofNullable(partial.${lambda.method}(${parameterNameString}));
}
</#macro>
<#-- @formatter:on -->