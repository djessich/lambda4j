package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleToLongFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleToLongFunction2 function = DoubleToLongFunction2.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleToLongFunction2 function = DoubleToLongFunction2.of(null);
        Assertions.assertNull(function);
    }
}
