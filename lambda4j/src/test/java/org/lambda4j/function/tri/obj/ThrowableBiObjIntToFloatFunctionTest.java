package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjIntToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjIntToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjIntToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
