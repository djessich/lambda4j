package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongSupplier<Exception> supplier = ThrowableLongSupplier.of(() -> Long.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongSupplier<Exception> supplier = ThrowableLongSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
