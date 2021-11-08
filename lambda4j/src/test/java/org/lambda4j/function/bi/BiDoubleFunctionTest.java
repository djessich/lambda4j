package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiDoubleFunction<String> function = BiDoubleFunction.of((value1, value2) -> Double.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiDoubleFunction<String> function = BiDoubleFunction.of((BiDoubleFunction<String>) null);
        Assertions.assertNull(function);
    }
}
