<#-- @formatter:off -->
<#import "../utils/filters.ftl" as filters>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda is primitive -->
<#if lambda.primitive>
    <#-- build a generic parameter type string including primitives also -->
    <#assign genericParameterTypeStringWithPrimitives = .namespace.buildGenericParameterTypeStringWithPrimitives()>
    <#-- get output lambda using a search for lambda type, arity, primitive and throwable flag -->
    <#assign outputLambda = LambdaUtils.search(lambda.type, lambda.arity, false, lambda.throwable)>
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
    return this::${lambda.type.method};
}
</#macro>

<#-- a helper function to build a generic parameter type string with primitives for given target lambda -->
<#function buildGenericParameterTypeStringWithPrimitives target = lambda>
    <#local parameters = [target.inputOneType!"", target.inputTwoType!"", target.inputThreeType!"", target.returnType!""]>
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