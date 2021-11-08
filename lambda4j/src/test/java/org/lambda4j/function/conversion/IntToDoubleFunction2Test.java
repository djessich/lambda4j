package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntToDoubleFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntToDoubleFunction2 function = IntToDoubleFunction2.of(value -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntToDoubleFunction2 function = IntToDoubleFunction2.of(null);
        Assertions.assertNull(function);
    }
}
