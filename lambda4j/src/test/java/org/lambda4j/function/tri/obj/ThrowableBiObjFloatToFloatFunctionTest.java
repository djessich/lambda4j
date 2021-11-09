package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjFloatToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjFloatToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
