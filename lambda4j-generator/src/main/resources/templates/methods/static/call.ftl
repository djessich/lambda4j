<#-- @formatter:off -->
<#import "../../utils/throwable.ftl" as throwable>
<#import "../../utils/types.ftl" as types>

<#-- TODO suppliers not get input so there must not be a ',' -->
<#-- TODO Javadoc: generic operator has param for inputs and return type with same name (@param <T> ... 2x) -->
<#-- TODO generic operator must not have '? extends T' -->
<#-- TODO generic operator of arity greater than 2 has method inputs with same name (... operator, T t, T t) -->
<#-- TODO generic operator of arity greater than 2 has lambda inputs with same parameter name ( return (t, t) -> ... ) -->
<#-- TODO cannot return void result from consumer (... return consumer.accept(...); ...) -->
/**
 * Calls the given {@link ${lambda.name}} with the given arguments and returns its result.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param ${lambda.type.simpleName} The ${lambda.type.simpleName} to be called
<#include "../../javadoc/paramArgumentInput.ftl">
 * @return The result from the given {@code ${lambda.name}}.
<#include "../../javadoc/throwsNullPointerException.ftl">
 */
static ${genericParameterTypeString} ${types.buildParameterType(lambda.returnType)} call(${annotation.nonnull} final ${lambda.name}${genericParameterTypeStringWithErasure} ${lambda.type.simpleName}, ${parameterString}) <@throwable.printThrowableDeclaration/> {
    Objects.requireNonNull(${lambda.type.simpleName});
    return ${lambda.type.simpleName}.${lambda.method}(${parameterNameString});
}
<#-- @formatter:on -->