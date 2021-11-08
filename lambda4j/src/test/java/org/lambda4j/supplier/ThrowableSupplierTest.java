package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Exception> supplier = ThrowableSupplier.of(() -> "");
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableSupplier<String, Exception> supplier = ThrowableSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
