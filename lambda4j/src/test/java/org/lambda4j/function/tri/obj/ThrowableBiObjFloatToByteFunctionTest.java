package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatToByteFunction<String, String, Throwable> function =
                ThrowableBiObjFloatToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatToByteFunction<String, String, Throwable> function =
                ThrowableBiObjFloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
