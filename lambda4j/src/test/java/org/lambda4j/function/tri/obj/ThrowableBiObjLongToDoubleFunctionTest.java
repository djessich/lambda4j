package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongToDoubleFunction<String, String, Exception> function =
                ThrowableBiObjLongToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongToDoubleFunction<String, String, Exception> function =
                ThrowableBiObjLongToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
