<#-- @formatter:off -->
<#import "../../utils/helpers.ftl" as helpers>
<#import "../../utils/throwable.ftl" as throwable>
<#import "../../utils/types.ftl" as types>

<#-- TODO Javadoc: generic operator has param for inputs and return type with same name (@param <T> ... 2x) -->
/**
 * Calls the given {@link ${lambda.name}} with the given arguments and returns its result.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param ${lambda.type.simpleName} The ${lambda.type.simpleName} to be called
<#include "../../javadoc/paramArgumentInput.ftl">
 * @return The result from the given {@code ${lambda.name}}.
<#include "../../javadoc/throwsNullPointerException.ftl">
 */
static ${genericParameterTypeString} ${types.buildParameterType(lambda.returnType)} call(${annotation.nonnull} final ${lambda.name}${genericParameterTypeStringWithErasure} ${lambda.type.simpleName} ${(lambda.arity > 0)?then(", ${parameterString}", "")}) <@throwable.printThrowableDeclaration/> {
    Objects.requireNonNull(${lambda.type.simpleName});
    ${helpers.printReturnIfNotVoid()} ${lambda.type.simpleName}.${lambda.method}(${parameterNameString});
}
<#-- @formatter:on -->