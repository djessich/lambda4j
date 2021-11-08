package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongSupplier2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongSupplier2 supplier = LongSupplier2.of(() -> Long.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongSupplier2 supplier = LongSupplier2.of(null);
        Assertions.assertNull(supplier);
    }
}
