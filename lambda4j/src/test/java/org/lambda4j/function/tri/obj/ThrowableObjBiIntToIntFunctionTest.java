package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiIntToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiIntToIntFunction<String, Throwable> function =
                ThrowableObjBiIntToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiIntToIntFunction<String, Throwable> function = ThrowableObjBiIntToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
