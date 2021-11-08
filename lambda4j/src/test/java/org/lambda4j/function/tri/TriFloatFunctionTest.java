package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriFloatFunction<String> function = TriFloatFunction.of((value1, value2, value3) -> Float.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriFloatFunction<String> function = TriFloatFunction.of((TriFloatFunction<String>) null);
        Assertions.assertNull(function);
    }
}
