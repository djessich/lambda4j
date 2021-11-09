package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatFunction<String, String, String, Throwable> function =
                ThrowableBiObjFloatFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatFunction<String, String, String, Throwable> function = ThrowableBiObjFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
