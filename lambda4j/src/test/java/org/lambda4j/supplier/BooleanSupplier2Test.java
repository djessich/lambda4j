package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanSupplier2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanSupplier2 supplier = BooleanSupplier2.of(() -> false);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanSupplier2 supplier = BooleanSupplier2.of(null);
        Assertions.assertNull(supplier);
    }
}
