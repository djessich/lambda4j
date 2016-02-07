<#-- @formatter:off -->

<#-- parse only if lambda is of type predicate -->
<#if LambdaUtils.isOfTypePredicate(lambda)>
    <@.namespace.isEqualMethod/>
</#if>

<#-- a helper macro to centralize isEqual method and to avoid unnecessary indenting -->
<#macro isEqualMethod>
/**
 * Returns a {@link ${lambda.name}} that tests if the given arguments are equal to the ones of this ${lambda.type.simpleName}
 * according to {@link Objects#equals(Object)} method.
 *
<#include "../../javadoc/paramGenericInput.ftl">
 * @param targetRef1 The first object reference with which to compare for equality, which may be {@code null}
 * @param targetRef2 The second object reference with which to compare for equality, which may be {@code null}
 * @param targetRef3 The third object reference with which to compare for equality, which may be {@code null}
 * @return A {@code ${lambda.name}} that tests if the given arguments are equal to the ones of this ${lambda.type.simpleName}.
 * @see #isNotEqual(Object, Object, Object)
 */
${annotation.nonnull}
static ${genericParameterTypeString} ${lambda.name}${genericParameterTypeString} isEqual(${annotation.nonnull} Object targetRef1, @Nullable Object targetRef2,
        @Nullable Object targetRef3) {
    return (t, u, v) -> (t == null ? targetRef1 == null : t.equals(targetRef1))
            && (u == null ? targetRef2 == null : u.equals(targetRef2))
            && (v == null ? targetRef3 == null : v.equals(targetRef3));
}
</#macro>
<#-- @formatter:on -->