package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiShortToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiShortToDoubleFunction<String, Exception> function =
                ThrowableObjBiShortToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiShortToDoubleFunction<String, Exception> function = ThrowableObjBiShortToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
