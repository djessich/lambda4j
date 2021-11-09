package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjIntToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntToByteFunction<String, String, Throwable> function =
                ThrowableBiObjIntToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntToByteFunction<String, String, Throwable> function =
                ThrowableBiObjIntToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
