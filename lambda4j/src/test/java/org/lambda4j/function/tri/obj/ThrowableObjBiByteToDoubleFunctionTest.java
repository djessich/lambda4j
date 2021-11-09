package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiByteToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiByteToDoubleFunction<String, Throwable> function =
                ThrowableObjBiByteToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiByteToDoubleFunction<String, Throwable> function =
                ThrowableObjBiByteToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
