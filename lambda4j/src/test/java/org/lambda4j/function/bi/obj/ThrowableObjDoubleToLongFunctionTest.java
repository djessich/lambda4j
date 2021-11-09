package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjDoubleToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjDoubleToLongFunction<String, Throwable> function =
                ThrowableObjDoubleToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjDoubleToLongFunction<String, Throwable> function = ThrowableObjDoubleToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
