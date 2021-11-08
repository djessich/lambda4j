package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiShortFunction<String> function = BiShortFunction.of((value1, value2) -> Short.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiShortFunction<String> function = BiShortFunction.of((BiShortFunction<String>) null);
        Assertions.assertNull(function);
    }
}
