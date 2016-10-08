<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- print only if lambda is throwable -->
<#if lambda.throwable>
    <#-- search for correct output lambda -->
    <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, false, false)>
    <#-- print method -->
    <@.namespace.sneakyThrowMethod outputLambda/>
</#if>

<#-- a helper macro to centralize orElse method and to avoid unnecessary indenting -->
<#macro sneakyThrowMethod outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input and sneakily throws the
 * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
 * each throwable thrown from the returned composed ${outputLambda.type.simpleName} behaves exactly the same as an <em>unchecked</em>
 * throwable does. As a result, there is no need to handle the throwable of this ${lambda.type.simpleName} in the returned composed
 * ${outputLambda.type.simpleName} by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
 * as it would be done in a non sneaky throwing ${outputLambda.type.simpleName}.
 * <p>
 * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
 * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
 * regardless of the {@code throws} clause of methods, which is why this works at all.
 * <p>
 * However, when using this method to get a sneaky throwing ${outputLambda.type.simpleName} variant of this throwable ${lambda.type.simpleName}, the
 * following advantages, disadvantages and limitations will apply:
 * <p>
 * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
 * clause of the method that applies the returned composed ${outputLambda.type.simpleName}. The compiler will not force the declaration in
 * the {@code throws} clause anymore.
 * <p>
 * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
 * {@code throws} clause of the method that applies the returned composed ${outputLambda.type.simpleName}. If not added, the compiler will
 * error that the caught throwable is never thrown in the corresponding {@code try} block.
 * <p>
 * If the returned composed ${outputLambda.type.simpleName} is directly surrounded by a {@code try}-{@code catch} block to catch the
 * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
 * corresponding {@code try} block.
 * <p>
 * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
 * returned composed ${outputLambda.type.simpleName}, the calling-code won't be able to catch the throwable by name. It will bubble and
 * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
 * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
 * intended.
 * <p>
 * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
 * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
 * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
 * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
 * {@code throws} clause of the method that applies the returned composed ${outputLambda.type.simpleName}.
 * <p>
 * With all that mentioned, the following example will demonstrate this methods correct use:
 * <pre>{@code
 * // when called with illegal value ClassNotFoundException is thrown
 * public Class<?> sneakyThrowingFunctionalInterface(final String className) throws ClassNotFoundException {
 *     return ThrowableFunction.of(Class::forName) // create the correct throwable functional interface
 *                .sneakyThrow() // create a non-throwable variant which is able to sneaky throw (this method)
 *                .apply(className); // apply non-throwable variant -> may sneaky throw a throwable
 * }
 *
 * // call the the method which surround the sneaky throwing functional interface
 * public void callingMethod() {
 *     try {
 *         final Class<?> clazz = sneakyThrowingFunctionalInterface("some illegal class name");
 *         // ... do something with clazz ...
 *     } catch(ClassNotFoundException e) {
 *         // ... do something with e ...
 *     }
 * }
 * }</pre>
 * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
 * disadvantages and limitations described above kept in mind.
 *
 * @return A composed {@link ${outputLambda.name}} that applies this ${lambda.type.simpleName} to its input and sneakily throws the
 * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
 * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is and thus not sneakily thrown.
 */
${annotation.nonnull}
default ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda)} sneakyThrow() {
    return (${parameterNameString}) -> {
        try {
            ${helpers.printReturnIfNotVoid()} this.${lambda.method}(${parameterNameString});
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable throwable) {
            throw ThrowableUtils.sneakyThrow(throwable);
        }
    };
}
</#macro>
<#-- @formatter:on -->