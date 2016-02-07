<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/tuple.ftl" as tuple>
<#-- TODO find correct output lambda for BiObjByteFunction -> which is ObjByteFunction -->
<#-- parse only if lambda has arity greater than 2 and at least two generics -->
<#if (lambda.arity >= 2)  && !helpers.isPrimitive(lambda.firstInputType!"") && !helpers.isPrimitive(lambda.secondInputType!"")>
    <#-- search for correct output lambda of tupled method -->
    <#assign outputLambda = LambdaUtils.searchByReturnType(lambda.type, 1, lambda.returnType, lambda.throwable)>
    <#-- if lambda does not have primitive return type, generate generic return for tupled method -->
    <#if !helpers.isPrimitive(outputLambda.returnType)>
        <#assign return = ", ${lambda.returnType}">
    </#if>
    <#-- print tupled method -->
    <@.namespace.tupledMethod return!""/>
</#if>

<#-- a helper macro to centralize tupled method and to avoid unnecessary indenting -->
<#macro tupledMethod return = "">
/**
 * Returns a tupled version of this ${lambda.type.simpleName}.
 *
 * @return A tupled version of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${outputLambda.name}<${tuple.printTuple()} ${return}> tupled() {
    return this::${lambda.type.method};
}
</#macro>
<#-- @formatter:on -->