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

package at.gridtec.lambda4j.supplier;

import java.util.function.Supplier;

/**
 * Represents a supplier of {@code byte}-valued results.  This is the {@code byte}-producing primitive specialization
 * of {@link Supplier}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsByte()}.
 *
 * @see ThrowableByteSupplier
 * @see Supplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteSupplier {

    /**
     * Creates a {@link ByteSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableByteSupplier} which always returns a given value.
     */
    static ByteSupplier constant(byte ret) {
        return () -> ret;
    }

    /**
     * Gets the supplied result from this {@link ByteSupplier}.
     *
     * @return The supplied result.
     */
    byte getAsByte();

    /**
     * Returns a composed {@link Supplier} which represents this {@link ByteSupplier}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatSupplier} with JRE specific methods, only accepting {@code Supplier}.
     *
     * @return A composed {@code Supplier} which represents this {@code ByteSupplier}.
     */
    default Supplier<Byte> boxed() {
        return this::getAsByte;
    }
}
