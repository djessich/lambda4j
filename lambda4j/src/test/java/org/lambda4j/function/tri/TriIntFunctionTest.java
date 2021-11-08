package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriIntFunction<String> function = TriIntFunction.of((value1, value2, value3) -> Integer.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriIntFunction<String> function = TriIntFunction.of((TriIntFunction<String>) null);
        Assertions.assertNull(function);
    }
}
