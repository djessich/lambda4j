package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjFloatToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjFloatToLongFunction<String, Throwable> function =
                ThrowableObjFloatToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjFloatToLongFunction<String, Throwable> function = ThrowableObjFloatToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
