package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = ThrowableSupplier.of(() -> "");
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableSupplier<String, Throwable> supplier = ThrowableSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
