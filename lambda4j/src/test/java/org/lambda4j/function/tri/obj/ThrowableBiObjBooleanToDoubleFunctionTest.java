package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBooleanToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanToDoubleFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanToDoubleFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
