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
package org.lambda4j.generator.exception;

import org.lambda4j.generator.entities.LambdaEntity;
import org.lambda4j.generator.processors.Processor;

/**
 * Implements a {@link RuntimeException} which indicated, that the {@link Processor#next(LambdaEntity)} had not been called in
 * a {@code Processor} to invoke next {@code Processor}.
 *
 * @deprecated As next method is not required to be called in each {@code Processor} and returning an instance directly
 * should be allowed if necessary, this exception type is not being used anymore. But keep in mind, that not calling
 * next method stops the chain immediately.
 */
@Deprecated()
public final class NextNotCalledException extends RuntimeException {

    /**
     * The processor on which next has not been called.
     */
    private final Processor processor;

    /**
     * Creates this exception to signal that {@link Processor#next} was not called.
     *
     * @param processor The processor on which the next method was not called
     */
    public NextNotCalledException(final Processor processor) {
        super("next() was not called in " + processor.getClass().getSimpleName());
        this.processor = processor;
    }

}
