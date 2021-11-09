package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjByteToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjByteToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjByteToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjByteToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjByteToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
