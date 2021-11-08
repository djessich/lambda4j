package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatSupplier supplier = FloatSupplier.of(() -> Float.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatSupplier supplier = FloatSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
