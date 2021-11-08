package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharSupplier supplier = CharSupplier.of(() -> Character.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharSupplier supplier = CharSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
