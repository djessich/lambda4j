package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatToDoubleFunction<String, String, Exception> function =
                ThrowableBiObjFloatToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatToDoubleFunction<String, String, Exception> function =
                ThrowableBiObjFloatToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
