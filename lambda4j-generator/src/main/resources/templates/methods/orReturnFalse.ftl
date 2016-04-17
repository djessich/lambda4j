<#-- @formatter:off -->
<#import "../utils/types.ftl" as types>

<#-- print only if lambda is throwable and of type predicate -->
<#if lambda.throwable && LambdaUtils.isOfTypePredicate(lambda)>
    <#-- search for correct output lambda -->
    <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, false, false)>
    <#-- search for correct throwable consuming lambda -->
    <#assign throwableConsumerLambda = LambdaUtils.searchByFirstInputAndReturnType(LambdaUtils.getConsumerType(), 1, Object, void, false, true)>
    <#-- print method -->
    <@.namespace.orReturnFalseMethod outputLambda />
    <@.namespace.orReturnFalseConsumerMethod outputLambda throwableConsumerLambda />
</#if>

<#-- a helper macro to centralize orReturnFalse method and to avoid unnecessary indenting -->
<#macro orReturnFalseMethod outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns {@code
 * false} if a {@link Throwable} is thrown from this one. If the {@code Throwable} is of type {@link Error} it
 * is rethrown as is, otherwise it is ignored.
 *
 * @return A composed {@code ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns {@code
 * false}, if a {@code Throwable} is thrown from this one.
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} orReturnFalse() {
    return (${parameterNameString}) -> {
        try {
            return ${lambda.method}(${parameterNameString});
        } catch (Error e) {
            throw e;
        } catch (Throwable ignored) {
            return false;
        }
    };
}
</#macro>

<#-- a helper macro to centralize orReturnFalse with consumer method and to avoid unnecessary indenting -->
<#macro orReturnFalseConsumerMethod outputLambda throwableConsumerLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns {@code
 * false} if a {@link Throwable} is thrown from this one. If the {@code Throwable} is of type {@link Error} it
 * is rethrown as is, otherwise it is consumed using the supplied consumer.
 *
 * @param throwableConsumer The consumer which consumes the thrown {@code Throwable} of this ${lambda.type.simpleName}
 * @return A composed {@code ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns {@code
 * false}, if a {@code Throwable} is thrown from this one.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} orReturnFalse(${annotation.nonnull} final ${throwableConsumerLambda.name}${types.buildGenericParameterTypeStringWithErasure(throwableConsumerLambda, "Throwable")} throwableConsumer) {
    Objects.requireNonNull(throwableConsumer);
    return (${parameterNameString}) -> {
        try {
            return ${lambda.method}(${parameterNameString});
        } catch (Error e) {
            throw e;
        } catch (Throwable throwable) {
            throwableConsumer.accept(throwable);
            return false;
        }
    };
}
</#macro>
<#-- @formatter:on -->