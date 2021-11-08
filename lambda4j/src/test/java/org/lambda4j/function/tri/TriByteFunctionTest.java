package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriByteFunction<String> function = TriByteFunction.of((value1, value2, value3) -> Byte.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriByteFunction<String> function = TriByteFunction.of((TriByteFunction<String>) null);
        Assertions.assertNull(function);
    }
}
