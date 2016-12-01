<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- execute only if lambda has arity greater than or equal to 2 -->
<#if (lambda.arity >= 2)>
    <#-- set argument lists -->
    <#assign argumentTypes = [lambda.firstInputType!"", lambda.secondInputType!"", lambda.thirdInputType!"", ""]>
    <#-- loop over range (which depends on arity) and print only method -->
    <#list (lambda.arity - 1)..!0 as current>
        <#-- get actual number from numbers array -->
        <#assign number = numbers[current?index]>
        <#-- set default values of output parameter -->
        <#assign inputType1 = "">
        <#assign inputType2 = "">
        <#assign outputType1 = "">
        <#assign outputType2 = "">
        <#-- lambda arity equals 3 (e.g. TriFunction), we need to prepare either one input and two output types or two input and one output type -->
        <#if (lambda.arity == 3)>
            <#if (current == 2)> <#-- first run -->
                <#assign inputType1 = argumentTypes[0]> <#-- T -->
                <#assign outputType1 = argumentTypes[1]> <#-- U -->
                <#assign outputType2 = argumentTypes[2]> <#-- V -->
            <#else>
                <#assign inputType1 = argumentTypes[0]> <#-- T -->
                <#assign inputType2 = argumentTypes[1]> <#-- U -->
                <#assign outputType1 = argumentTypes[2]> <#-- V -->
            </#if>
        <#-- if lambda arity equals 2 (e.g. BiFunction), we will only need to prepare one input and one output type -->
        <#elseif (lambda.arity == 2)>
                <#assign inputType1 = argumentTypes[0]> <#-- T -->
                <#assign outputType1 = argumentTypes[1]> <#-- U -->
        </#if>
        <#-- search for correct output lambda of partial method, which is an equal lambda to the global lambda with decremented arity -->
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(current, outputType1, lambda.thirdInputType!lambda.secondInputType, Object, lambda.returnType, lambda.throwable, false)>
        <#-- print partial method -->
        <@.namespace.partialMethod outputLambda inputType1 inputType2 outputType1 outputType2 />
    </#list>

    <#-- If lambda has a generic first argument and the all the other arguments are primitive, then we can add some more partial methods (e.g. ObjFloatFunction, BiObjByteFunction) -->
    <#if !helpers.isPrimitive(lambda.firstInputType) && (helpers.isPrimitive(lambda.secondInputType) || helpers.isPrimitive(lambda.thirdInputType))>
        <#-- loop over range (which depends on arity) and print only method -->
        <#list (lambda.arity - 1)..!0 as current>
             <#-- set default values of output parameter -->
            <#assign inputType1 = "">
            <#assign inputType2 = "">
            <#assign outputType1 = "">
            <#assign outputType2 = "">
            <#-- lambda arity equals 3, we need to prepare either one input and two output types or two input and one output type -->
            <#if (lambda.arity == 3)>
                <#if helpers.isPrimitive(lambda.secondInputType)> <#-- ObjBiByteFunction -->
                     <#if (current == 2)> <#-- first run -->
                        <#assign inputType1 = argumentTypes[1]> <#-- byte value -->
                        <#assign outputType1 = argumentTypes[0]> <#-- T t -->
                        <#assign outputType2 = argumentTypes[2]> <#-- byte value -->
                     <#else>
                        <#assign inputType1 = argumentTypes[1]> <#-- byte value1 -->
                        <#assign inputType2 = argumentTypes[2]> <#-- byte value2 -->
                        <#assign outputType1 = argumentTypes[0]> <#-- T t -->
                    </#if>
                    <#-- search for correct output lambda of partial method, which is an equal lambda to the global lambda with decremented arity -->
                    <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(current, outputType1, lambda.secondInputType, Object, lambda.returnType, lambda.throwable, false)>
                <#else> <#-- BiObjByteFunction -->
                    <#if (current == 2)> <#-- first run -->
                        <#assign inputType1 = argumentTypes[2]> <#-- byte value -->
                        <#assign outputType1 = argumentTypes[0]> <#-- T t -->
                        <#assign outputType2 = argumentTypes[1]> <#-- U u -->
                    <#else>
                        <#assign inputType1 = argumentTypes[0]> <#-- T t -->
                        <#assign inputType2 = argumentTypes[2]> <#-- byte value -->
                        <#assign outputType1 = argumentTypes[1]> <#-- U u -->
                    </#if>
                    <#-- search for correct output lambda of partial method, which is an equal lambda to the global lambda with decremented arity -->
                    <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(current, outputType1, Object, Object, lambda.returnType, lambda.throwable, false)>
                </#if>
            <#else> <#-- e.g. ObjByteFunction -->
                <#assign inputType1 = argumentTypes[1]> <#-- byte -->
                <#assign outputType1 = argumentTypes[0]> <#-- T -->
                <#-- search for correct output lambda of partial method, which is an equal lambda to the global lambda with decremented arity -->
                <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(current, outputType1, Object, Object, lambda.returnType, lambda.throwable, false)>
            </#if>
            <#-- print partial method -->
            <@.namespace.partialMethod outputLambda inputType1 inputType2 outputType1 outputType2 />
        </#list>
    </#if>
</#if>

<#-- a helper macro to centralize partial method and to avoid unnecessary indenting -->
<#macro partialMethod outputLamba inputType1 = "" inputType2 = "" outputType1 = "" outputType2 = "">
/**
 * Applies this ${inputLambda.type.simpleName} partially to some arguments of this one, producing a {@link ${outputLamba.name}} as result.
 *
<@.namespace.javadocArgumentInputPartialMethod inputType1/>
<@.namespace.javadocArgumentInputPartialMethod inputType2/>
 * @return A {@code ${outputLamba.name}} that represents this ${inputLambda.type.simpleName} partially applied the some arguments.
 */
${annotation.nonnull}
default ${outputLamba.name}${types.buildGenericParameterTypeString(outputLamba, outputType1, outputType2)} p${lambda.method}(${.namespace.buildInputTypeString(inputType1, inputType2)}) {
    return (${.namespace.buildOutputTypeNameString(outputType1, outputType2)}) -> this.${lambda.method}(${parameterNameString});
}
</#macro>

<#-- a helper macro which prints the arguments of this method regarding the arguments of global lambda -->
<#macro javadocArgumentInputPartialMethod type = "">
<#if type?has_content && lambda.firstInputType?has_content && (lambda.firstInputType.typeSimpleName == type.typeSimpleName) && (lambda.firstInputType.count == type.count)>
 * @param ${types.buildParameterName(type)} The first argument to this ${lambda.type.simpleName} used to partially apply this function
<#elseif type?has_content && lambda.secondInputType?has_content && (lambda.secondInputType.typeSimpleName == type.typeSimpleName) && (lambda.secondInputType.count == type.count)>
 * @param ${types.buildParameterName(type)} The second argument to this ${lambda.type.simpleName} used to partially apply this function
<#elseif type?has_content && lambda.thirdInputType?has_content && (lambda.thirdInputType.typeSimpleName == type.typeSimpleName) && (lambda.thirdInputType.count == type.count)>
 * @param ${types.buildParameterName(type)} The third argument to this ${lambda.type.simpleName} used to partially apply this function
</#if>
</#macro>

<#-- a helper function to build a string of parameters for the two given types -->
<#function buildInputTypeString type1 = "" type2 = "">
    <#assign ret = "">
    <#if type1?has_content>
        <#assign ret = ret + types.buildParameter(type1)>
    </#if>
    <#if type2?has_content>
        <#assign ret = ret + "," + types.buildParameter(type2)>
    </#if>
    <#return ret>
</#function>

<#-- a helper function to build a string of parameter names for the two given types -->
<#function buildOutputTypeNameString type1 = "" type2 = "">
    <#assign ret = "">
    <#if type1?has_content>
        <#assign ret = ret + types.buildParameterName(type1)>
    </#if>
    <#if type2?has_content>
        <#assign ret = ret + "," + types.buildParameterName(type2)>
    </#if>
    <#return ret>
</#function>
<#-- @formatter:on -->