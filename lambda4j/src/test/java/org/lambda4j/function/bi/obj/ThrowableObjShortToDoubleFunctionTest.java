package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjShortToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjShortToDoubleFunction<String, Throwable> function =
                ThrowableObjShortToDoubleFunction.of((t, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjShortToDoubleFunction<String, Throwable> function = ThrowableObjShortToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
