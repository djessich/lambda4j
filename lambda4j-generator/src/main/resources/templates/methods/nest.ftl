<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- print only if lambda is throwable -->
<#if lambda.throwable>
    <#-- search for correct output lambda -->
    <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, false, false)>
    <#-- print methods -->
    <@.namespace.nestMethod outputLambda/>
    <@.namespace.nestWithMethod outputLambda/>
</#if>

<#-- a helper macro to centralize nest method and to avoid unnecessary indenting -->
<#macro nestMethod outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input and nests the
 * thrown {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
 * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
 *
 * @return A composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input and nests the
 * thrown {@code Throwable} from it.
 * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
 * @see #nest(Function)
 * @see ThrownByFunctionalInterfaceException
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} nest() {
    return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
}
</#macro>

<#-- a helper macro to centralize nestWith method and to avoid unnecessary indenting -->
<#macro nestWithMethod outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input and nests the
 * thrown {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code Throwable},
 * regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
 *
 * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
 * @return A composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input and nests the
 * thrown {@code Throwable} from it using {@code mapper} operation.
<#include "../javadoc/throwsNullPointerException.ftl">
 * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
 * @see #nest()
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} nest(${annotation.nonnull} final Function<? super Throwable, ? extends RuntimeException> mapper) {
    return recover(throwable -> {
        throw mapper.apply(throwable);
    });
}
</#macro>
<#-- @formatter:on -->