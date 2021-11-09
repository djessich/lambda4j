package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjByteToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjByteToDoubleFunction<String, String, Throwable> function =
                ThrowableBiObjByteToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjByteToDoubleFunction<String, String, Throwable> function =
                ThrowableBiObjByteToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
