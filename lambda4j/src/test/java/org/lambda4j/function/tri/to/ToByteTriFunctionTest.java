package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToByteTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToByteTriFunction<String, String, String> function = ToByteTriFunction.of((t, u, v) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToByteTriFunction<String, String, String> function = ToByteTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
