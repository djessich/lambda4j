package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiByteFunction<String, String, Throwable> function =
                ThrowableObjBiByteFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiByteFunction<String, String, Throwable> function = ThrowableObjBiByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
