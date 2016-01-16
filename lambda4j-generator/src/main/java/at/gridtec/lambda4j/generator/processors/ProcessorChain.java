/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
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

import at.gridtec.lambda4j.generator.Lambda;
import at.gridtec.lambda4j.generator.exception.NotProcessableException;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents the chain of all {@link Processor}s to manipulate {@code Lambda}s.
 */
public class ProcessorChain {

    /**
     * The only Singleton instance of this class.
     */
    private static final ProcessorChain INSTANCE = new ProcessorChain();

    /**
     * The {@link Processor} to be invoked.
     */
    private volatile Processor processor = null;

    /**
     * Private constructor to prevent instantiation as this class is implemented as Singleton.
     */
    private ProcessorChain() {

    }

    /**
     * Returns the only Singleton instance of this class.
     *
     * @return The only Singleton instance of this class.
     */
    @Nonnull
    public static ProcessorChain getInstance() {
        return INSTANCE;
    }

    /**
     * Adds a processor to the chain.
     *
     * @param processor The processor to be added to the chain
     * @throws NullPointerException If given argument is {@code null}
     */
    public void addProcessor(@Nonnull final Processor processor) {
        Objects.requireNonNull(processor);
        if (this.processor == null) {
            this.processor = processor;
        } else {
            processor.setNextProcessor(this.processor);
            this.processor = processor;
        }
    }

    /**
     * Adds processors to the chain.
     *
     * @param processors The processors to be added to the chain
     * @throws NullPointerException If given argument is {@code null}
     */
    public void addProcessor(@Nonnull final Processor[] processors) {
        Objects.requireNonNull(processors);
        Arrays.asList(processors).forEach(this::addProcessor);
    }

    /**
     * Adds processors to the chain.
     *
     * @param processors The processors to be added to the chain
     * @throws NullPointerException If given argument is {@code null}
     */
    public void addProcessor(@Nonnull final Collection<Processor> processors) {
        Objects.requireNonNull(processors);
        processors.forEach(this::addProcessor);
    }

    /**
     * This method invokes the chain by calling the known {@link Processor} to this class, which is the {@code
     * Processor} added at last to this class..
     *
     * @return The result from chain invocation.
     */
    @Nonnull
    public List<Lambda> invoke() {
        List<Lambda> lambdas = new LinkedList<>();
        if (this.processor != null) {
            final Lambda lambda = new Lambda();
            if (this.processor.processable(lambda)) {
                lambdas.addAll(this.processor.process(lambda));
            } else {
                throw new NotProcessableException(this.processor, lambda);
            }
        }
        return lambdas;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singletons are not cloneable");
    }
}
