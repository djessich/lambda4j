package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjShortToLongFunction<String, Throwable> function =
                ThrowableObjShortToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjShortToLongFunction<String, Throwable> function = ThrowableObjShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
