package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoubleToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoubleToByteFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoubleToByteFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
