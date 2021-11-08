package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharSupplier<Exception> supplier = ThrowableCharSupplier.of(() -> Character.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharSupplier<Exception> supplier = ThrowableCharSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
