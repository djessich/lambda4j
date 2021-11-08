package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjCharToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharToDoubleFunction<String, String, Exception> function =
                ThrowableBiObjCharToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharToDoubleFunction<String, String, Exception> function =
                ThrowableBiObjCharToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
