package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiIntFunction<String, String, Throwable> function =
                ThrowableObjBiIntFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiIntFunction<String, String, Throwable> function = ThrowableObjBiIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
