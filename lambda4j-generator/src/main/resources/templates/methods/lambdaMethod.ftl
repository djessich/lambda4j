<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/tuple.ftl" as tuple>

/**
 * Applies this ${lambda.type.simpleName} to the given arguments.
 *
<#include "../javadoc/paramArgumentInput.ftl">
 * @return The return value from the function, which is its result.
 */
${lambda.returnType.typeName} ${lambda.type.method}(${parameterString});

<#-- print tupled apply only if lambda has arity greater than 2 and at least two generics -->
<#if (lambda.arity >= 2) && !helpers.isPrimitive(lambda.firstInputType) && !helpers.isPrimitive(lambda.secondInputType)>
/**
 * Applies this ${lambda.type.simpleName} to the given tuple.
 *
 * @param tuple The tuple to be applied to the ${lambda.type.simpleName}
 * @return The return value from the ${lambda.type.simpleName}, which is its result.
<#include "../javadoc/throwsNullPointerException.ftl">
<#include "../javadoc/seeTupleClass.ftl">
 */
default ${lambda.returnType.typeName} ${lambda.type.method}(${annotation.nonnull} ${tuple.printTuple()} tuple ${helpers.isPrimitive(lambda.thirdInputType)?then(", " + types.buildParameter(lambda.thirdInputType), "")}) {
    Objects.requireNonNull(tuple);
    return ${lambda.type.method}(${tuple.printTupleAccess()} ${helpers.isPrimitive(lambda.thirdInputType)?then(", " + types.buildParameterName(lambda.thirdInputType), "")});
}
</#if>
<#-- @formatter:on -->