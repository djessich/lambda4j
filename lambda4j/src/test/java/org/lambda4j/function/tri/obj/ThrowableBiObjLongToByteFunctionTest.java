package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongToByteFunction<String, String, Throwable> function =
                ThrowableBiObjLongToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongToByteFunction<String, String, Throwable> function =
                ThrowableBiObjLongToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
