package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjLongToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjLongToLongFunction<String, Throwable> function =
                ThrowableObjLongToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjLongToLongFunction<String, Throwable> function = ThrowableObjLongToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
