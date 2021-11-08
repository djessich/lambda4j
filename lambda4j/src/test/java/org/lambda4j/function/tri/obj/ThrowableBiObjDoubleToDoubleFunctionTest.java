package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoubleToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoubleToDoubleFunction<String, String, Exception> function =
                ThrowableBiObjDoubleToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoubleToDoubleFunction<String, String, Exception> function =
                ThrowableBiObjDoubleToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
