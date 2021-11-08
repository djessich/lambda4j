package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiByteToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiByteToByteFunction<String> function = ObjBiByteToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiByteToByteFunction<String> function = ObjBiByteToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
