package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteSupplier<Throwable> supplier = ThrowableByteSupplier.of(() -> Byte.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteSupplier<Throwable> supplier = ThrowableByteSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
