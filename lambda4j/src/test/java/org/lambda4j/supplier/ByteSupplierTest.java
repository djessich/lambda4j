package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteSupplier supplier = ByteSupplier.of(() -> Byte.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteSupplier supplier = ByteSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}
