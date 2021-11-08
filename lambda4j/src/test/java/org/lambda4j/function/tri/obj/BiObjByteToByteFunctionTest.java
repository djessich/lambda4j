package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjByteToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjByteToByteFunction<String, String> function =
                BiObjByteToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjByteToByteFunction<String, String> function = BiObjByteToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
