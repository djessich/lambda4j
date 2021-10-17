/*
 * Copyright (c) 2021 The lambda4j authors.
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

package org.lambda4j.runnable;

import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;

/**
 * The {@link Runnable} interface should be implemented by any class whose instances are intended to be executed by a
 * thread. The class must define a method of no arguments called {@link #run()}. This extension is able to throw any
 * {@link Throwable}.
 * <p>
 * This interface is designed to provide a common protocol for objects that wish to execute code while they are active.
 * For example, {@code Runnable} is implemented by class {@link Thread}. Being active simply means that a thread has
 * been started and has not yet been stopped.
 * <p>
 * In addition, {@code Runnable} provides the means for a class to be active while not subclassing {@code Thread}. A
 * class that implements {@code Runnable} can run without subclassing {@code Thread} by instantiating a {@code Thread}
 * instance and passing itself in as the target. In most cases, the {@code Runnable} interface should be used if you are
 * only planning to override the {@link #run()} method and no other {@code Thread} methods. This is important because
 * classes should not be subclassed unless the programmer intends on modifying or enhancing the fundamental behavior of
 * the class.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #run()}.
 *
 * @apiNote This is a JDK lambda.
 * @see Runnable
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface Runnable2 extends Lambda, Runnable {

    /**
     * Constructs a {@link Runnable2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code Runnable2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static Runnable2 of(@Nullable Runnable2 expression) {
        return expression;
    }

    /**
     * Calls the given {@link Runnable} with the given argument and returns its result.
     *
     * @param runnable The runnable to be called
     * @throws NullPointerException If given argument is {@code null}
     */
    static void call(@Nonnull Runnable runnable) {
        Objects.requireNonNull(runnable);
        runnable.run();
    }

    /**
     * Applies this runnable to the given argument.
     */
    @Override
    void run();

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
     * Returns a composed {@link Runnable2} that performs, in sequence, this runnable followed by the {@code after}
     * runnable. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this runnable throws an exception, the {@code after} runnable will not be performed.
     *
     * @param after The runnable to apply after this runnable is applied
     * @return A composed {@link Runnable2} that performs, in sequence, this runnable followed by the {@code after}
     * runnable.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default Runnable2 andThen(@Nonnull Runnable after) {
        Objects.requireNonNull(after);
        return () -> {
            run();
            after.run();
        };
    }

}
