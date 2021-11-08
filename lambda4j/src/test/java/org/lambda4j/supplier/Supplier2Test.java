package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Supplier2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = Supplier2.of(() -> "");
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        Supplier2<String> supplier = Supplier2.of(null);
        Assertions.assertNull(supplier);
    }
}
