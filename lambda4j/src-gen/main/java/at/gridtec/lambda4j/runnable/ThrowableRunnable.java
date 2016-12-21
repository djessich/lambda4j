/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.gridtec.lambda4j.runnable;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Function;

@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableRunnable<X extends Throwable> extends Lambda, Runnable {

    /**
     * Constructs a {@link ThrowableRunnable} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <X> The type of the throwable to be thrown by this runnable
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableRunnable} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableRunnable<X> of(@Nullable final ThrowableRunnable<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableRunnable} with the given argument and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this runnable
     * @param runnable The runnable to be called
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this runnables action
     */
    static <X extends Throwable> void call(@Nonnull final ThrowableRunnable<? extends X> runnable) throws X {
        Objects.requireNonNull(runnable);
        runnable.runThrows();
    }

    /**
     * Applies this runnable to the given argument.
     *
     * @throws X Any throwable from this runnables action
     */
    void runThrows() throws X;

    /**
     * Applies this runnable to the given argument.
     *
     * @apiNote This method mainly exists to use this {@link ThrowableRunnable} in JRE specific methods only accepting
     * {@link Runnable}. If this runnable should be applied, then the {@link #runThrows()} method should be used.
     * @apiNote Overrides the {@link Runnable#run()} method by using a redefinition as default method. This
     * implementation calls the {@link #runThrows()} method of this function and catches the eventually thrown {@link
     * Throwable} from it. If it is of type {@link RuntimeException} or {@link Error} it is rethrown as is. Other {@code
     * Throwable} types are wrapped in a {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default void run() {
        // TODO: Remove commented code below
    /*try {
          this.runThrows();
    } catch (RuntimeException | Error e) {
        throw e;
    } catch (Throwable throwable) {
        throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
    }*/
        nest().run();
    }

    /**
     * Returns the number of arguments for this runnable.
     *
     * @return The number of arguments for this runnable.
     * @implSpec The default implementation always returns {@code 0}.
     */
    @Nonnegative
    default int arity() {
        return 0;
    }

    /**
     * Returns a composed {@link ThrowableRunnable} that performs, in sequence, this runnable followed by the {@code
     * after} runnable. If performing this runnable throws an exception, the {@code after} runnable will not be
     * performed.
     *
     * @param after The runnable to apply after this runnable is applied
     * @return A composed {@link ThrowableRunnable} that performs, in sequence, this runnable followed by the {@code
     * after} runnable.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableRunnable<X> andThen(@Nonnull final ThrowableRunnable<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> {
            runThrows();
            after.runThrows();
        };
    }

    /**
     * Returns a composed {@link Runnable2} that applies this runnable to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link Runnable2} that applies this runnable to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default Runnable2 nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link Runnable2} that applies this runnable to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link Runnable2} that applies this runnable to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default Runnable2 nest(@Nonnull final Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link Runnable2} that first applies this runnable to its input, and then applies the {@code
     * recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is represented
     * by a curried operation which is called with throwable information and same argument of this runnable.
     *
     * @param recover The operation to apply if this runnable throws a {@code Throwable}
     * @return A composed {@link Runnable2} that first applies this runnable to its input, and then applies the {@code
     * recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing runnable is {@code null}
     * @implSpec The implementation checks that the returned enclosing runnable from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default Runnable2 recover(@Nonnull final Function<? super Throwable, ? extends Runnable> recover) {
        Objects.requireNonNull(recover);
        return () -> {
            try {
                this.runThrows();
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                final Runnable runnable = recover.apply(throwable);
                Objects.requireNonNull(runnable, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                runnable.run();
            }
        };
    }

    /**
     * Returns a composed {@link Runnable2} that applies this runnable to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed runnable behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this runnable in the returned composed
     * runnable by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing runnable.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing runnable variant of this throwable runnable, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed runnable. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed runnable. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed runnable is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed runnable, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed runnable.
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
     * @return A composed {@link Runnable2} that applies this runnable to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default Runnable2 sneakyThrow() {
        return () -> {
            try {
                this.runThrows();
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}