package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjLongFunction<String, String, Exception> function = ThrowableObjLongFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjLongFunction<String, String, Exception> function = ThrowableObjLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
