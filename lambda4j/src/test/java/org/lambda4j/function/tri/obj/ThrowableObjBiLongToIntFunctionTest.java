package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiLongToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiLongToIntFunction<String, Throwable> function =
                ThrowableObjBiLongToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiLongToIntFunction<String, Throwable> function = ThrowableObjBiLongToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
