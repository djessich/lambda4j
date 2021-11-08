package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriDoubleFunction<String> function = TriDoubleFunction.of((value1, value2, value3) -> Double.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriDoubleFunction<String> function = TriDoubleFunction.of((TriDoubleFunction<String>) null);
        Assertions.assertNull(function);
    }
}
