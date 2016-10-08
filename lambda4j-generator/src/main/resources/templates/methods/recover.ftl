<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#if lambda.throwable>
    <#-- search for correct output lambda, which is the non-throwing variant of this lambda -->
    <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, false, false)>
    <#-- search for correct enclosing lambda, which is the jdk equivalent/variant of this lambda -->
    <#assign enclosingLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, false, true)>
    <#-- print recover method -->
    <@.namespace.recoverMethod outputLambda enclosingLambda />
</#if>

<#-- a helper macro to centralize recover method and to avoid unnecessary indenting -->
<#macro recoverMethod outputLambda enclosingLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies the
 * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is represented by a curried
 * operation which is called with throwable information and same argument${helpers.s()} of this ${lambda.type.simpleName}.
 *
 * @param recover The operation to apply if this ${lambda.type.simpleName} throws a {@code Throwable}
 * @return A composed {@link ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies the
 * {@code recover} operation if a {@code Throwable} is thrown from this one.
 * @throws NullPointerException If given argument or the returned enclosing ${enclosingLambda.type.simpleName} is {@code null}
 * @implSpec The implementation checks that the returned enclosing ${enclosingLambda.type.simpleName} from {@code recover} operation is not {@code null}.
 * If it is, then a {@link NullPointerException} with appropriate message is thrown.
 * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code recover} operation.
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} recover(${annotation.nonnull} final Function<? super Throwable, ? extends ${enclosingLambda.name}${types.buildGenericParameterTypeStringWithErasure(enclosingLambda)}> recover) {
    Objects.requireNonNull(recover);
    return (${parameterNameString}) -> {
        try {
            ${helpers.printReturnIfNotVoid(outputLambda)} this.${lambda.method}(${parameterNameString});
        } catch (Error e) {
            throw e;
        } catch (Throwable throwable) {
            final ${enclosingLambda.name}${types.buildGenericParameterTypeStringWithErasure(enclosingLambda)} ${enclosingLambda.type.simpleName} = recover.apply(throwable);
            Objects.requireNonNull(${enclosingLambda.type.simpleName}, () -> "recover returned null for " + throwable.getClass() + ": " + throwable.getMessage());
            ${helpers.printReturnIfNotVoid(outputLambda)} ${enclosingLambda.type.simpleName}.${enclosingLambda.method}(${parameterNameString});
        }
    };
}
</#macro>
<#-- @formatter:on -->