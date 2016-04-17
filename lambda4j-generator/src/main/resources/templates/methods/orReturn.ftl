<#-- @formatter:off -->
<#import "../utils/types.ftl" as types>

<#-- print only if lambda is throwable, has return (so lambda cannot be of type consumer or runnable) and is not of type predicate (those will get special orReturn methods) -->
<#if lambda.throwable && !LambdaUtils.isOfTypeConsumer(lambda) && !LambdaUtils.isOfTypeRunnable(lambda) && !LambdaUtils.isOfTypePredicate(lambda) && lambda.returnType?has_content>
    <#-- search for correct output lambda -->
    <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, false, false)>
    <#-- search for correct throwable consuming lambda -->
    <#assign throwableConsumerLambda = LambdaUtils.searchByFirstInputAndReturnType(LambdaUtils.getConsumerType(), 1, Object, void, false, true)>
    <#-- search for correct supplier lambda -->
    <#assign supplierLambda = LambdaUtils.searchByReturnType(0, lambda.returnType, false, true)>
    <#-- build return type -->
    <#assign returnValue = types.buildParameterType(lambda.returnType)>
    <#-- print method -->
    <@.namespace.orReturnMethod outputLambda returnValue/>
    <@.namespace.orReturnConsumerMethod outputLambda returnValue throwableConsumerLambda/>
    <@.namespace.orReturnSupplierMethod outputLambda supplierLambda/>
    <@.namespace.orReturnSupplierAndConsumerMethod outputLambda supplierLambda throwableConsumerLambda/>
</#if>

<#-- a helper macro to centralize orReturn method and to avoid unnecessary indenting -->
<#macro orReturnMethod outputLambda returnValue>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns {@code
 * otherValue} if a {@link Throwable} is thrown from this one. If the {@code Throwable} is of type {@link Error} it
 * is rethrown as is, otherwise it is ignored.
 *
 * @param otherValue The value to return if this ${lambda.type.simpleName} throws a {@code Throwable}
 * @return A composed {@code ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns {@code
 * otherValue}, if a {@code Throwable} is thrown from this one.
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} orReturn(${returnValue} otherValue) {
    return (${parameterNameString}) -> {
        try {
            return ${lambda.method}(${parameterNameString});
        } catch (Error e) {
            throw e;
        } catch (Throwable ignored) {
            return otherValue;
        }
    };
}
</#macro>

<#-- a helper macro to centralize orReturn with consumer method and to avoid unnecessary indenting -->
<#macro orReturnConsumerMethod outputLambda returnValue throwableConsumerLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns {@code
 * otherValue} if a {@link Throwable} is thrown from this one. If the {@code Throwable} is of type {@link Error} it
 * is rethrown as is, otherwise it is consumed using the supplied consumer.
 *
 * @param otherValue The value to return if this ${lambda.type.simpleName} throws a {@code Throwable}
 * @param throwableConsumer The consumer which consumes the thrown {@code Throwable} of this ${lambda.type.simpleName}
 * @return A composed {@code ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns {@code
 * otherValue}, if a {@code Throwable} is thrown from this one.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} orReturn(${returnValue} otherValue, ${annotation.nonnull} final ${throwableConsumerLambda.name}${types.buildGenericParameterTypeStringWithErasure(throwableConsumerLambda, "Throwable")} throwableConsumer) {
    Objects.requireNonNull(throwableConsumer);
    return (${parameterNameString}) -> {
        try {
            return ${lambda.method}(${parameterNameString});
        } catch (Error e) {
            throw e;
        } catch (Throwable throwable) {
            throwableConsumer.accept(throwable);
            return otherValue;
        }
    };
}
</#macro>

<#-- a helper macro to centralize orReturn with supplier method and to avoid unnecessary indenting -->
<#macro orReturnSupplierMethod outputLambda supplierLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns the
 * supplied value of {@code otherValueSupplier} if a {@link Throwable} is thrown from this one. If the {@code
 * Throwable} is of type {@link Error} it is rethrown as is, otherwise it is ignored.
 *
 * @param otherValueSupplier The value to return if this ${lambda.type.simpleName} throws a {@code Throwable}
 * @return A composed {@code ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns the
 * supplied value of {@code otherValueSupplier} if a {@code Throwable} is thrown from this one.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} orReturn(${annotation.nonnull} final ${supplierLambda.name}${types.buildGenericParameterTypeStringWithErasure(supplierLambda)} otherValueSupplier) {
    Objects.requireNonNull(otherValueSupplier);
    return (${parameterNameString}) -> {
        try {
            return ${lambda.method}(${parameterNameString});
        } catch (Error e) {
            throw e;
        } catch (Throwable ignored) {
            return otherValueSupplier.${supplierLambda.method}();
        }
    };
}
</#macro>

<#-- a helper macro to centralize orReturn with supplier and consumer method and to avoid unnecessary indenting -->
<#macro orReturnSupplierAndConsumerMethod outputLambda supplierLambda throwableConsumerLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns the
 * supplied value of {@code otherValueSupplier} if a {@link Throwable} is thrown from this one. If the {@code
 * Throwable} is of type {@link Error} it is rethrown as is, otherwise it is consumed using the supplied consumer.
 *
 * @param otherValueSupplier The value to return if this ${lambda.type.simpleName} throws a {@code Throwable}
 * @param throwableConsumer The consumer which consumes the thrown {@code Throwable} of this ${lambda.type.simpleName}
 * @return A composed {@code ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input, and then returns the
 * supplied value of {@code otherValueSupplier} if a {@code Throwable} is thrown from this one.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} orReturn(${annotation.nonnull} final ${supplierLambda.name}${types.buildGenericParameterTypeStringWithErasure(supplierLambda)} otherValueSupplier,
        ${annotation.nonnull} final ${throwableConsumerLambda.name}${types.buildGenericParameterTypeStringWithErasure(throwableConsumerLambda, "Throwable")} throwableConsumer) {
    Objects.requireNonNull(otherValueSupplier);
    Objects.requireNonNull(throwableConsumer);
    return (${parameterNameString}) -> {
        try {
            return ${lambda.method}(${parameterNameString});
        } catch (Error e) {
            throw e;
        } catch (Throwable throwable) {
            throwableConsumer.accept(throwable);
            return otherValueSupplier.${supplierLambda.method}();
        }
    };
}
</#macro>
<#-- @formatter:on -->