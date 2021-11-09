package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleSupplier<Throwable> supplier = ThrowableDoubleSupplier.of(() -> Double.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleSupplier<Throwable> supplier = ThrowableDoubleSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
