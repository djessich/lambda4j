package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntToLongFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntToLongFunction2 function = IntToLongFunction2.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntToLongFunction2 function = IntToLongFunction2.of(null);
        Assertions.assertNull(function);
    }
}
