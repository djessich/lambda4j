package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjDoubleFunction<String, String, Throwable> function = ThrowableObjDoubleFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjDoubleFunction<String, String, Throwable> function = ThrowableObjDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
