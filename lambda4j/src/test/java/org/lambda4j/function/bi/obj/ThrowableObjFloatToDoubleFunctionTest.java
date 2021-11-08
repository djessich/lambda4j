package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjFloatToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjFloatToDoubleFunction<String, Exception> function =
                ThrowableObjFloatToDoubleFunction.of((t, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjFloatToDoubleFunction<String, Exception> function = ThrowableObjFloatToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
