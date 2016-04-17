<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- print only if lambda is throwable -->
<#if lambda.throwable>
    <#assign throwableConsumerLambda = LambdaUtils.searchByFirstInputAndReturnType(LambdaUtils.getConsumerType(), 1, Object, void, false, true)>
    <@.namespace.orElseMethod/>
    <@.namespace.orElseConsumerMethod throwableConsumerLambda/>
</#if>

<#-- a helper macro to centralize orElse method and to avoid unnecessary indenting -->
<#macro orElseMethod>
/**
 * Returns a composed {@link ${lambda.name}} that applies this ${lambda.type.simpleName} to its input, and then applies the {@code
 * other} ${lambda.type.simpleName} if a {@link Throwable} is thrown from this one. If the {@code Throwable} is of type {@link Error}
 * it is rethrown as is, otherwise it is ignored.
 *
 * @param other The ${lambda.type.simpleName} to apply if this ${lambda.type.simpleName} throws a {@code Throwable}
 * @return A composed {@code ${lambda.name}} that applies this ${lambda.type.simpleName} to its input, and then applies the {@code other}
 * ${lambda.type.simpleName}, if a {@code Throwable} is thrown from this one.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeString} orElse(${annotation.nonnull} final ${lambda.name}${genericParameterTypeStringWithErasure} other) {
    Objects.requireNonNull(other);
    return (${parameterNameString}) -> {
        try {
             ${helpers.printReturnIfNotVoid()} ${lambda.method}(${parameterNameString});
        } catch (Error e) {
            throw e;
        } catch (Throwable ignored) {
            ${helpers.printReturnIfNotVoid()} other.${lambda.method}(${parameterNameString});
        }
    };
}
</#macro>

<#-- a helper macro to centralize orElse with consumer method and to avoid unnecessary indenting -->
<#macro orElseConsumerMethod throwableConsumerLambda>
/**
 * Returns a composed {@link ${lambda.name}} that applies this ${lambda.type.simpleName} to its input, and then applies the {@code
 * other} ${lambda.type.simpleName} if a {@link Throwable} is thrown from this one. If the {@code Throwable} is of type {@link Error}
 * it is rethrown as is, otherwise it is consumed using the supplied consumer.
 *
 * @param other The ${lambda.type.simpleName} to apply if this ${lambda.type.simpleName} throws a {@code Throwable}
 * @param throwableConsumer The consumer which consumes the thrown {@code Throwable} of this ${lambda.type.simpleName}
 * @return A composed {@code ${lambda.name}} that applies this ${lambda.type.simpleName} to its input, and then applies the {@code other}
 * ${lambda.type.simpleName}, if a {@code Throwable} is thrown from this one.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeString} orElse(${annotation.nonnull} final ${lambda.name}${genericParameterTypeStringWithErasure} other,
        ${annotation.nonnull} final ${throwableConsumerLambda.name}${types.buildGenericParameterTypeStringWithErasure(throwableConsumerLambda, "Throwable")} throwableConsumer) {
    Objects.requireNonNull(other);
    Objects.requireNonNull(throwableConsumer);
    return (${parameterNameString}) -> {
        try {
            ${helpers.printReturnIfNotVoid()} ${lambda.method}(${parameterNameString});
        } catch (Error e) {
            throw e;
        } catch (Throwable throwable) {
            throwableConsumer.accept(throwable);
            ${helpers.printReturnIfNotVoid()} other.${lambda.method}(${parameterNameString});
        }
    };
}
</#macro>
<#-- @formatter:on -->