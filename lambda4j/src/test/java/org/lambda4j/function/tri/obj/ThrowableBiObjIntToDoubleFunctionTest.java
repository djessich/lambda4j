package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjIntToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntToDoubleFunction<String, String, Throwable> function =
                ThrowableBiObjIntToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntToDoubleFunction<String, String, Throwable> function =
                ThrowableBiObjIntToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
