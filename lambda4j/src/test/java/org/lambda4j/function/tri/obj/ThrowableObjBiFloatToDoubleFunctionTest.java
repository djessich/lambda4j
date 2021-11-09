package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiFloatToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiFloatToDoubleFunction<String, Throwable> function =
                ThrowableObjBiFloatToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiFloatToDoubleFunction<String, Throwable> function = ThrowableObjBiFloatToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
