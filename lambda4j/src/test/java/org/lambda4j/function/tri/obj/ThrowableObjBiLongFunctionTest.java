package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiLongFunction<String, String, Throwable> function =
                ThrowableObjBiLongFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiLongFunction<String, String, Throwable> function = ThrowableObjBiLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
