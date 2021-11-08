package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiCharToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiCharToByteFunction<String> function = ObjBiCharToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiCharToByteFunction<String> function = ObjBiCharToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
