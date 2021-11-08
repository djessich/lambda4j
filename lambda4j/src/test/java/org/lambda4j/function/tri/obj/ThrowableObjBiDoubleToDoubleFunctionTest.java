package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiDoubleToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiDoubleToDoubleFunction<String, Exception> function =
                ThrowableObjBiDoubleToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiDoubleToDoubleFunction<String, Exception> function =
                ThrowableObjBiDoubleToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
