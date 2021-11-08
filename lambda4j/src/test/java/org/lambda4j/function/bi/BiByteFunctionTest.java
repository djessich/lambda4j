package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiByteFunction<String> function = BiByteFunction.of((value1, value2) -> Byte.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiByteFunction<String> function = BiByteFunction.of((BiByteFunction<String>) null);
        Assertions.assertNull(function);
    }
}
