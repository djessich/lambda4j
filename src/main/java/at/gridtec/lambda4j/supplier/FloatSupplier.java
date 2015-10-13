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

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * Represents a supplier of {@code float}-valued results.  This is the {@code float}-producing primitive specialization
 * of {@link Supplier}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsFloat()}.
 *
 * @see java.util.function.Supplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatSupplier {

    /**
     * Creates a {@link FloatSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatSupplier} which always returns a given value.
     */
    @Nonnull
    static FloatSupplier constant(float ret) {
        return () -> ret;
    }

    /**
     * Gets the supplied result from this supplier.
     *
     * @return The supplied result.
     */
    float getAsFloat();

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 0;
    }

    /**
     * Returns a composed {@link Supplier} which represents this {@link FloatSupplier}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatSupplier} with JRE specific methods, only accepting {@code Supplier}.
     *
     * @return A composed {@code Supplier} which represents this {@code FloatSupplier}.
     */
    @Nonnull
    default Supplier<Float> boxed() {
        return this::getAsFloat;
    }
}
