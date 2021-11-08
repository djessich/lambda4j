package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjShortToByteFunction<String, String> function =
                BiObjShortToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjShortToByteFunction<String, String> function = BiObjShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
