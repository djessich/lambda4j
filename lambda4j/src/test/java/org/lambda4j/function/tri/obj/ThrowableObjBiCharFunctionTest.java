package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiCharFunction<String, String, Throwable> function =
                ThrowableObjBiCharFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiCharFunction<String, String, Throwable> function = ThrowableObjBiCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
