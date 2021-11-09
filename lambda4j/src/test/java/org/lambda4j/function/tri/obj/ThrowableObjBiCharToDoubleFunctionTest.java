package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiCharToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiCharToDoubleFunction<String, Throwable> function =
                ThrowableObjBiCharToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiCharToDoubleFunction<String, Throwable> function = ThrowableObjBiCharToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
