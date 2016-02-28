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
package at.gridtec.lambda4j.generator.processors;

import at.gridtec.lambda4j.generator.entities.LambdaEntity;
import at.gridtec.lambda4j.generator.exception.NotProcessableException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents the base type for all {@link Processor}s available in the {@link Processor} chain. To create a new {@code
 * Processor} this class must be extended and {@link #process(LambdaEntity)} must be implemented as it will always be
 * called from previous {@code Processor}. The next {@code Processor} can be set using {@link
 * #setNextProcessor(Processor)}.
 */
// TODO avoid use of PRIMITIVES List; get all primitive classes from a lib
public abstract class Processor {

    protected static final List<Class<?>> PRIMITIVES = Arrays.asList(boolean.class, byte.class, char.class,
                                                                     double.class, float.class, int.class, long.class,
                                                                     short.class).stream()
            //            .map(Class::getSimpleName)
            .collect(Collectors.toList());

    /**
     * The next {@link Processor} to be executed after this {@code Processor} has been executed.
     */
    private Processor nextProcessor;

    /**
     * Returns the next {@link Processor} to be executed after this {@code Processor} has been executed.
     *
     * @return The next {@code Processor} to be executed after this {@code Processor} has been executed.
     */
    @Nullable
    public final Processor getNextProcessor() {
        return this.nextProcessor;
    }

    /**
     * This methods sets the next {@link Processor} to be executed after this {@code Processor} has been executed.
     *
     * @param next The next processor to be set
     */
    public final void setNextProcessor(@Nullable final Processor next) {
        Objects.requireNonNull(next);
        this.nextProcessor = next;
    }

    /**
     * This method must be overridden in implementing classes to check if a {@link LambdaEntity} is able to be handled.
     * If the {@link Processor} is not able to handle this lambda, an appropriate exception will be thrown.
     *
     * @param lambda The lambda to be checked
     * @return {@code true} if, and only if, the given lambda is able to be handled
     * @throws NullPointerException If given argument is {@code null}
     */
    protected abstract boolean processable(@Nonnull final LambdaEntity lambda);

    /**
     * This method must be overridden in implementing classes to handle the given {@link LambdaEntity} by the chain. By
     * calling {@link #next(LambdaEntity)} the next handler will be invoked. The return value represents the result from
     * next step, if there is one.
     *
     * @param lambda The lambda to be processed
     * @return The result from next {@code Processor}.
     * @throws NullPointerException If given argument is {@code null}
     * @see #next(LambdaEntity)
     */
    @Nonnull
    protected abstract List<LambdaEntity> process(@Nonnull final LambdaEntity lambda);

    /**
     * Helper method to call the next {@link Processor} only, if it has been set (not {@code null}). If there is no next
     * {@code Processor} the given lambda is wrapped in a {@link List} which is returned.
     *
     * @param lambda The lambda to be processed
     * @return The result from next {@code Processor} or the given lambda wrapped in a {@code List} if there is no next
     * {@code Processor}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    @Nonnull
    protected final List<LambdaEntity> next(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        if (this.nextProcessor != null) {
            return invoke(lambda);
        } else {
            return Arrays.asList(lambda);
        }
    }

    /**
     * This implements a helper method for calling all callbacks in appropriate order.
     *
     * @param lambda The lambda to be processed
     * @return The result from next {@code Processor} or the given lambda wrapped in a {@code List} if there is no next
     * {@code Processor}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    private List<LambdaEntity> invoke(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        if (this.nextProcessor.processable(lambda)) {
            return this.nextProcessor.process(lambda);
        } else {
            throw new NotProcessableException(this.nextProcessor, lambda);
        }
    }
}
