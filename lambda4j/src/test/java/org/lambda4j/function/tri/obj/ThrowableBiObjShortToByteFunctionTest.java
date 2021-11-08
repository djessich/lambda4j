package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortToByteFunction<String, String, Exception> function =
                ThrowableBiObjShortToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortToByteFunction<String, String, Exception> function =
                ThrowableBiObjShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
