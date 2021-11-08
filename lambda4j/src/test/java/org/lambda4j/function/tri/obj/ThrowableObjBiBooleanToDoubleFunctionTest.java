package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBooleanToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBooleanToDoubleFunction<String, Exception> function =
                ThrowableObjBiBooleanToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBooleanToDoubleFunction<String, Exception> function =
                ThrowableObjBiBooleanToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
