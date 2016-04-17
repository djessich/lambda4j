<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- print only if lambda is throwable -->
<#if lambda.throwable>
    <#-- search for correct output lambda -->
    <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, false, false)>
    <#-- print method -->
    <@.namespace.nestMethod outputLambda/>
</#if>

<#-- a helper macro to centralize orElse method and to avoid unnecessary indenting -->
<#macro nestMethod outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input and nests the thrown
 * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
 * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
 * message and the thrown throwable itself.
 *
 * @return A composed {@code ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input and nests the thrown {@code
 * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} nest() {
    return (${parameterNameString}) -> {
        try {
            ${helpers.printReturnIfNotVoid()} this.${lambda.method}(${parameterNameString});
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
        }
    };
}
</#macro>
<#-- @formatter:on -->