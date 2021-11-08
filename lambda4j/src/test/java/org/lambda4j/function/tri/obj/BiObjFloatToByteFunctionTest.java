package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjFloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjFloatToByteFunction<String, String> function =
                BiObjFloatToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjFloatToByteFunction<String, String> function = BiObjFloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
