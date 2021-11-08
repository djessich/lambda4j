package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjByteToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjByteToByteFunction<String, String, Exception> function =
                ThrowableBiObjByteToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjByteToByteFunction<String, String, Exception> function =
                ThrowableBiObjByteToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
