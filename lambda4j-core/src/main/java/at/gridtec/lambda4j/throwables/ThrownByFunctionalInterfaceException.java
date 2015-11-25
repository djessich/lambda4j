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
package at.gridtec.lambda4j.throwables;

import javax.annotation.Nullable;

/**
 * Represents a {@link RuntimeException} which is used to wrap all exceptions from a {@link FunctionalInterface}. This
 * brings the opportunity to easily throw all kind of {@link Throwable} from a {@code FunctionalInterface}.
 */
public class ThrownByFunctionalInterfaceException extends RuntimeException {

    /**
     * Constructs a new {@link RuntimeException} with {@code null} as its detail message.  The cause is not initialized,
     * and may subsequently be initialized by a call to {@link #initCause(Throwable)}.
     */
    public ThrownByFunctionalInterfaceException() {
        super();
    }

    /**
     * Constructs a new {@link RuntimeException} with the specified detail message. The cause is not initialized, and
     * may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message The detail message for the exception
     */
    public ThrownByFunctionalInterfaceException(@Nullable final String message) {
        super(message);
    }

    /**
     * Constructs a new {@link RuntimeException} with the specified cause. The detail message will be {@code null}.
     *
     * @param cause The cause of the exception
     */
    public ThrownByFunctionalInterfaceException(@Nullable final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new {@link RuntimeException} with the specified detail message and {@link Throwable} cause.
     *
     * @param message The detail message for the exception
     * @param cause The cause of the exception
     */
    public ThrownByFunctionalInterfaceException(@Nullable final String message, @Nullable final Throwable cause) {
        super(message, cause);
    }
}
