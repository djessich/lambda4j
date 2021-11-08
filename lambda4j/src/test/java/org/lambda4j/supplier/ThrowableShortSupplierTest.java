package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Exception> supplier = ThrowableShortSupplier.of(() -> Short.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortSupplier<Exception> supplier = ThrowableShortSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
