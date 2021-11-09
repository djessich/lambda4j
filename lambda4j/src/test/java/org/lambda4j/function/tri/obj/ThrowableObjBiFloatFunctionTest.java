package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiFloatFunction<String, String, Throwable> function =
                ThrowableObjBiFloatFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiFloatFunction<String, String, Throwable> function = ThrowableObjBiFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
