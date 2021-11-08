package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleSupplier2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleSupplier2 supplier = DoubleSupplier2.of(() -> Double.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleSupplier2 supplier = DoubleSupplier2.of(null);
        Assertions.assertNull(supplier);
    }
}
