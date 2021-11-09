package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjByteFunction<String, String, String, Throwable> function =
                ThrowableBiObjByteFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjByteFunction<String, String, String, Throwable> function = ThrowableBiObjByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
