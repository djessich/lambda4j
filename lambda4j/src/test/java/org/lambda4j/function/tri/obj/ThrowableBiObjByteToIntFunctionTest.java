package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjByteToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjByteToIntFunction<String, String, Throwable> function =
                ThrowableBiObjByteToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjByteToIntFunction<String, String, Throwable> function =
                ThrowableBiObjByteToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
