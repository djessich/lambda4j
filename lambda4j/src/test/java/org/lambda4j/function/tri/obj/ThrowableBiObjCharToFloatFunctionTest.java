package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjCharToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjCharToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjCharToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
