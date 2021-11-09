package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoubleToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoubleToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoubleToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
