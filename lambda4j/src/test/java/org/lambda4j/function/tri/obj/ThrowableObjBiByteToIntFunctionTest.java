package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiByteToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiByteToIntFunction<String, Throwable> function =
                ThrowableObjBiByteToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiByteToIntFunction<String, Throwable> function = ThrowableObjBiByteToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
