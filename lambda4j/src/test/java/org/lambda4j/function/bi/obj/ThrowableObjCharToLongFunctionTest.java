package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjCharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjCharToLongFunction<String, Throwable> function =
                ThrowableObjCharToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjCharToLongFunction<String, Throwable> function = ThrowableObjCharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
