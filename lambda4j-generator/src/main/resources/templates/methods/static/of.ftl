<#-- @formatter:off -->
/**
 * Constructs a {@link ${lambda.name}} based on a lambda expression or a method reference. Thereby the given lambda
 * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
 * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
 * @return A {@code ${lambda.name}} from given lambda expression or method reference.
 * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
 * null} will be returned.
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda Expression</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
 */
${annotation.nullable}
static ${genericParameterTypeString} ${lambda.name}${genericParameterTypeString} of(${annotation.nullable} final ${lambda.name}${genericParameterTypeString} expression) {
    return expression;
}
<#-- @formatter:on -->