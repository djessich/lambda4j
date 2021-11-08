package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongToDoubleFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongToDoubleFunction2 function = LongToDoubleFunction2.of(value -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongToDoubleFunction2 function = LongToDoubleFunction2.of(null);
        Assertions.assertNull(function);
    }
}
