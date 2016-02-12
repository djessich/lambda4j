<#-- @formatter:off -->
/**
 * Returns the number of arguments for this ${lambda.type.simpleName}.
 *
 * @return The number of arguments for this ${lambda.type.simpleName}.
 * @implSpec The default implementation always returns {@code ${lambda.arity}}.
 */
${annotation.nonnegative}
default int arity() {
    return ${lambda.arity};
}
<#-- @formatter:on -->