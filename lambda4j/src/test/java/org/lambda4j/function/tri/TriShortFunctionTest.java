package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriShortFunction<String> function = TriShortFunction.of((value1, value2, value3) -> Short.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriShortFunction<String> function = TriShortFunction.of((TriShortFunction<String>) null);
        Assertions.assertNull(function);
    }
}
