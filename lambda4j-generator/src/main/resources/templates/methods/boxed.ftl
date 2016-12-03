<#-- @formatter:off -->
<#import "../utils/filters.ftl" as filters>
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda has primitive input arguments, or is of type function or supplier with primitive return -->
<#if helpers.isPrimitiveLambdaInput(lambda) || ((LambdaUtils.isOfTypeFunction(lambda) || LambdaUtils.isOfTypeSupplier(lambda)) && helpers.isPrimitive(lambda.returnType))>
    <#-- build a generic parameter type string including primitives also -->
    <#assign genericParameterTypeStringWithPrimitives = .namespace.buildGenericParameterTypeStringWithPrimitives()>
    <#-- search for correct output lambda, which gets object (generical) inputs and object (generical) output, unless if global lambda represents a type a predicate or consumer type) -->
    <#if LambdaUtils.isOfTypePredicate(lambda) || LambdaUtils.isOfTypeConsumer(lambda)>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object,  lambda.returnType, lambda.throwable, false)>
    <#else>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object,  Object, lambda.throwable, false)>
    </#if>
    <#-- print boxed method -->
    <@.namespace.boxedMethod genericParameterTypeStringWithPrimitives outputLambda/>
</#if>

<#-- a helper macro to centralize boxed method and to avoid unnecessary indenting -->
<#macro boxedMethod genericParameterTypeStringWithPrimitives outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} which represents this {@link ${lambda.name}}. Thereby the primitive
 * input argument for this ${lambda.type.simpleName} is autoboxed. This method provides the possibility to use this
 * {@code ${lambda.name}} with methods provided by the {@code JDK}.
 *
 * @return A composed {@code ${outputLambda.name}} which represents this {@code ${lambda.name}}.
 */
${annotation.nonnull}
default ${outputLambda.name}${genericParameterTypeStringWithPrimitives} boxed() {
    return this::${lambda.method};
}
</#macro>

<#-- a helper function to build a generic parameter type string with primitives for given target lambda -->
<#function buildGenericParameterTypeStringWithPrimitives target = lambda>
    <#local target = LambdaUtils.copy(target)>
    <#local parameters = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local parameters = [target.returnType!""]>
    <#elseif !LambdaUtils.isOfTypePredicate(target) && !LambdaUtils.isOfTypeConsumer(target)>
        <#local parameters = parameters + [target.returnType!""]>
    </#if>
    <#local parameters = parameters + [target.throwableType!""]>
    <#local parameters = filters.filterEmpties(parameters)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list parameters as type>
        <#-- get type simple name from type as key for class map and save the keys value -->
            <#local genericString = genericString + types.buildGenericParameterType(type)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>
<#-- @formatter:on -->