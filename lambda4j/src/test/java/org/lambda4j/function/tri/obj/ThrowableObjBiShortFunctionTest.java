package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiShortFunction<String, String, Throwable> function =
                ThrowableObjBiShortFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiShortFunction<String, String, Throwable> function = ThrowableObjBiShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
