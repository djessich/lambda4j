package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntSupplier<Exception> supplier = ThrowableIntSupplier.of(() -> Integer.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntSupplier<Exception> supplier = ThrowableIntSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
