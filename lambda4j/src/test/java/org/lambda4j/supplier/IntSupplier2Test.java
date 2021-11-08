package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntSupplier2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = IntSupplier2.of(() -> Integer.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntSupplier2 supplier = IntSupplier2.of(null);
        Assertions.assertNull(supplier);
    }
}
