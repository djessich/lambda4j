package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleToIntFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleToIntFunction2 function = DoubleToIntFunction2.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleToIntFunction2 function = DoubleToIntFunction2.of(null);
        Assertions.assertNull(function);
    }
}
