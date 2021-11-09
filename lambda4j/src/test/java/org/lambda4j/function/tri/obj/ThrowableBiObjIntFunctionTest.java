package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntFunction<String, String, String, Throwable> function =
                ThrowableBiObjIntFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntFunction<String, String, String, Throwable> function = ThrowableBiObjIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
