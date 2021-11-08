package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriLongFunction<String> function = TriLongFunction.of((value1, value2, value3) -> Long.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriLongFunction<String> function = TriLongFunction.of((TriLongFunction<String>) null);
        Assertions.assertNull(function);
    }
}
