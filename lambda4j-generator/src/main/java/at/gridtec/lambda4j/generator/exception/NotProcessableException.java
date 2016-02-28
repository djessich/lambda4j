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
package at.gridtec.lambda4j.generator.exception;

import at.gridtec.lambda4j.generator.entities.LambdaEntity;
import at.gridtec.lambda4j.generator.processors.Processor;

/**
 * Implements a {@link RuntimeException} which is thrown to signalize that a lambda cannot be handled by a {@link
 * Processor}.
 */
public final class NotProcessableException extends RuntimeException {

    /**
     * The processor which failed to handle a lambda.
     */
    private final Processor processor;

    /**
     * The lambda which failed to be handled.
     */
    private final LambdaEntity lambda;

    /**
     * Creates this exception from given {@link Processor} and {@link LambdaEntity}, and generates a default message string.
     *
     * @param processor The processor which failed to handle the given lambda
     * @param lambda The lambda which could not be handled
     */
    public NotProcessableException(final Processor processor, final LambdaEntity lambda) {
        super(processor.getClass().getSimpleName() + " failed to handle a lambda");
        this.processor = processor;
        this.lambda = lambda;
    }

    /**
     * Creates this exception from given {@link Processor} and {@link LambdaEntity}, and generates a default message string .
     * Thereby the given {@code cause} is appended to this exception.
     *
     * @param processor The processor which failed to handle the given lambda
     * @param lambda The lambda which could not be handled
     * @param cause The cause of this exception
     */
    public NotProcessableException(final Processor processor, final LambdaEntity lambda, final Throwable cause) {
        super(processor.getClass().getName() + " failed to handle handle a lambda", cause);
        this.processor = processor;
        this.lambda = lambda;
    }

    /**
     * Returns the processor which failed to handle the lambda.
     *
     * @return The processor which failed to handle the lambda.
     */
    public Processor getProcessor() {
        return processor;
    }

    /**
     * Returns the lambda which failed to be handled.
     *
     * @return The lambda which failed to be handled.
     */
    public LambdaEntity getLambda() {
        return lambda;
    }
}
