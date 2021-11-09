package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatSupplier<Throwable> supplier = ThrowableFloatSupplier.of(() -> Float.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatSupplier<Throwable> supplier = ThrowableFloatSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
