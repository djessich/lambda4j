package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortSupplier supplier = ShortSupplier.of(() -> Short.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortSupplier supplier = ShortSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
