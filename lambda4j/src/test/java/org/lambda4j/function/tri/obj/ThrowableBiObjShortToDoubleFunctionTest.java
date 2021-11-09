package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortToDoubleFunction<String, String, Throwable> function =
                ThrowableBiObjShortToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortToDoubleFunction<String, String, Throwable> function =
                ThrowableBiObjShortToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
