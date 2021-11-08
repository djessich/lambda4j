package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiBooleanToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiBooleanToDoubleFunction function = BiBooleanToDoubleFunction.of((value1, value2) -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiBooleanToDoubleFunction function = BiBooleanToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
