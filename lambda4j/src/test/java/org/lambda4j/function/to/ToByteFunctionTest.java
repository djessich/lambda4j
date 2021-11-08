package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToByteFunction<String> function = ToByteFunction.of(Byte::parseByte);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToByteFunction<String> function = ToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
