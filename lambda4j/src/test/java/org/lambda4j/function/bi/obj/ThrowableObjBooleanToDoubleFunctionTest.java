package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanToDoubleFunction<String, Exception> function =
                ThrowableObjBooleanToDoubleFunction.of((t, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanToDoubleFunction<String, Exception> function = ThrowableObjBooleanToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
