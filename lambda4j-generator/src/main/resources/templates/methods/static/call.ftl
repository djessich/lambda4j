<#-- @formatter:off -->
<#import "../../utils/throwable.ftl" as throwable>

/**
 * Calls the given {@link ${lambda.name}} with the given arguments and returns its result.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param ${lambda.type.simpleName} The ${lambda.type.simpleName} to be called
<#include "../../javadoc/paramArgumentInput.ftl">
 * @return The result from the given {@code ${lambda.name}}.
<#include "../../javadoc/throwsNullPointerException.ftl">
 */
static ${genericParameterTypeString} ${lambda.returnType.typeName} call(${annotation.nonnull} final ${lambda.name}${genericParameterTypeStringWithErasure} ${lambda.type.simpleName}, ${parameterString}) <@throwable.printThrowableDeclaration/> {
    Objects.requireNonNull(${lambda.type.simpleName});
    return ${lambda.type.simpleName}.${lambda.type.method}(${parameterNameString});
}
<#-- @formatter:on -->