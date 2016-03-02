<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is not of type consumer or runnable (no return type) and has primitive return type -->
<#if !LambdaUtils.isOfTypeConsumer(lambda) && !LambdaUtils.isOfTypeRunnable(lambda) && helpers.isPrimitive(lambda.returnType)>
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
    <#-- foreach primitive type print method -->
    <#list primitives?keys as key>
        <#-- get primitive type -->
        <#assign primitive = primitives[key]>
        <#-- search for correct input lambda which gets the return type from global lambda as input and returns a primitive value -->
        <#assign inputLambda = LambdaUtils.searchByFirstInputAndReturnType(1, lambda.returnType, primitive, lambda.throwable, true)>
        <#-- search for correct output lambda which get all input types from global lambda and returns a primitive value -->
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, primitive, lambda.throwable, false)>
        <#-- print andThenToPrimitive method -->
        <@.namespace.andThenToPrimitiveMethod inputLambda outputLambda primitive.typeSimpleName/>
    </#list>
</#if>

<#-- a helper macro to centralize andThenToPrimitive method and to avoid unnecessary indenting -->
<#macro andThenToPrimitiveMethod inputLambda outputLambda primitiveType>
/**
 * Returns a composed {@link ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies
 * the {@code after} ${inputLambda.type.simpleName} to the result.
<#if !lambda.throwable>
 * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
</#if>
 * This method is just convenience, to provide the ability to transform this primitive ${lambda.type.simpleName} to an operation returning {@code ${primitiveType}}.
 *
 * @param after The ${inputLambda.type.simpleName} to apply after this ${lambda.type.simpleName} is applied
 * @return A composed {@code ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies
 * the {@code after} ${inputLambda.type.simpleName} to the result.
<#include "../javadoc/throwsNullPointerException.ftl">
 * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code ${primitiveType}}.
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} andThenTo${primitiveType?cap_first}(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda, lambda.returnType)} after) {
    Objects.requireNonNull(after);
    return (${parameterNameString}) -> after.${inputLambda.method}(${lambda.method}(${parameterNameString}));
}
</#macro>
<#-- @formatter:on -->