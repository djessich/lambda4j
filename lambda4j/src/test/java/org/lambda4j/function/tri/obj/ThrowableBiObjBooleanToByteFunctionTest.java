package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBooleanToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanToByteFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanToByteFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
