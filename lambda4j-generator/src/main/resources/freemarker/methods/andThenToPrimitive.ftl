<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is primitive -->
<#if helpers.isPrimitiveLambda(lambda)>
    <#assign primitives = {
        "boolean":boolean,
        "byte":byte,
        "char":char,
        "double":double,
        "float":float,
        "int":int,
        "long":long,
        "short":short
    }>
    <#list primitives?keys as key>
        <#assign primitive = primitives[key]>
        <#assign inputLambda = LambdaUtils.searchByFirstInputAndReturnType(1, lambda.returnType, primitive, lambda.throwable)>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, primitive, lambda.throwable)>
        <@.namespace.andThenToPrimitiveMethod inputLambda outputLambda primitive/>
    </#list>
</#if>

<#-- TODO access type entity using a lowercase simple name of its name -->
<#-- TODO recheck all parameters -->
<#-- a helper macro to centralize andThenToPrimitive method and to avoid unnecessary indenting -->
<#macro andThenToPrimitiveMethod inputLambda outputLambda primitiveType>
/**
 * Returns a composed {@link ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies
 * the {@code after} ${inputLambda.type.simpleName} to the result.
<#if lambda.throwable>
 * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
</#if>
 * This method is just convenience, to provide the ability to transform this operation to an operation returning {@code ${primitiveType}}.
 *
 * @param after The ${inputLambda.type.simpleName} to apply after this ${lambda.type.simpleName} is applied
 * @return A composed {@link ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies
 * the {@code after} ${inputLambda.type.simpleName} to the result.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} andThenTo${primitiveType?cap_first}(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda, lambda.returnType)} after) {
    Objects.requireNonNull(after);
    return (${parameterNameString}) -> after.${inputLambda.type.method}(${lambda.type.method}(${parameterNameString}));
}
</#macro>
<#-- @formatter:on -->