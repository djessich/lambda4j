package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortToFloatFunction<String, String, Exception> function =
                ThrowableBiObjShortToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortToFloatFunction<String, String, Exception> function =
                ThrowableBiObjShortToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
