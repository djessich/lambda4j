package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjLongToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongToFloatFunction<String, String, Throwable> function =
                ThrowableBiObjLongToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
