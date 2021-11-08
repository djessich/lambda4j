package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiFloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiFloatToByteFunction<String> function =
                ObjBiFloatToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiFloatToByteFunction<String> function = ObjBiFloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
