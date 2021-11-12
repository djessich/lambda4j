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

package org.lambda4j.exception;

/**
 * Represents an exception which wraps any thrown {@link Throwable} from a functional interface.
 */
public class ThrownByFunctionalInterfaceException extends RuntimeException {

    private static final long serialVersionUID = -6427088851299452019L;

    /**
     * Creates this exception from given {@link Throwable} cause.
     *
     * @param cause The cause of this exception.
     */
    public ThrownByFunctionalInterfaceException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates this exception from given message {@link String} and {@link Throwable} cause.
     *
     * @param message The message for this throwable.
     * @param cause The cause of this exception.
     */
    public ThrownByFunctionalInterfaceException(String message, Throwable cause) {
        super(message, cause);
    }
}
