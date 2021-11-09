package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiFloatToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiFloatToIntFunction<String, Throwable> function =
                ThrowableObjBiFloatToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiFloatToIntFunction<String, Throwable> function = ThrowableObjBiFloatToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
