package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjByteToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjByteToLongFunction<String, Throwable> function =
                ThrowableObjByteToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjByteToLongFunction<String, Throwable> function = ThrowableObjByteToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
