<#-- @formatter:off -->
<#import "../utils/filters.ftl" as filters>
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is primitive -->
<#if helpers.isPrimitiveLambda(lambda)>
    <#-- build a generic parameter type string including primitives also -->
    <#assign genericParameterTypeStringWithPrimitives = .namespace.buildGenericParameterTypeStringWithPrimitives()>
    <#-- search for correct output lambda, which gets object (generical) inputs and object (generical) output, unless if global lambda represents a type a predicate or consumer type) -->
    <#if LambdaUtils.isOfTypePredicate(lambda) || LambdaUtils.isOfTypeConsumer(lambda)>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object,  lambda.returnType, lambda.throwable)>
    <#else>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object,  Object, lambda.throwable)>
    </#if>
    <#-- print boxed method -->
    <@.namespace.boxedMethod genericParameterTypeStringWithPrimitives outputLambda/>
</#if>

<#-- a helper macro to centralize boxed method and to avoid unnecessary indenting -->
<#macro boxedMethod genericParameterTypeStringWithPrimitives outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} which represents this {@link ${lambda.name}}. Thereby the primitive
 * input argument for this ${lambda.type.simpleName} is autoboxed.
<#if lambda.jdk??>
 * This method is just convenience to provide the ability to use this {@code ${lambda.name}} with JRE specific methods,
 * only accepting {@code ${outputLambda.name}}.
</#if>
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
    <#local parameters = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#-- if lambda is of type operator just use return type; otherwise if not predicate or consumer append return type -->
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local parameters = [target.returnType!""]>
    <#elseif !LambdaUtils.isOfTypePredicate(target) && !LambdaUtils.isOfTypeConsumer(target)>
        <#local parameters = parameters + [target.returnType!""]>
    </#if>
    <#local parameters = filters.filterEmpties(parameters)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list parameters as type>
            <#local genericString = genericString + types.buildParameterType(type)?cap_first>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>
<#-- @formatter:on -->