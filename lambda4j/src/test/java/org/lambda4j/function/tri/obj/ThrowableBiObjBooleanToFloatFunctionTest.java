package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBooleanToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
