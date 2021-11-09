package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjDoubleToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjDoubleToDoubleFunction<String, Throwable> function =
                ThrowableObjDoubleToDoubleFunction.of((t, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjDoubleToDoubleFunction<String, Throwable> function = ThrowableObjDoubleToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
