package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiShortToLongFunction<String, Throwable> function =
                ThrowableObjBiShortToLongFunction.of((t, value1, value2) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiShortToLongFunction<String, Throwable> function = ThrowableObjBiShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
