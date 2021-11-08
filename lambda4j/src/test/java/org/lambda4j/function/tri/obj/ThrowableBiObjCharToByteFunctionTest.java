package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjCharToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharToByteFunction<String, String, Exception> function =
                ThrowableBiObjCharToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharToByteFunction<String, String, Exception> function =
                ThrowableBiObjCharToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
