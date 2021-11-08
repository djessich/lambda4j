package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiShortToIntFunction<String, Exception> function =
                ThrowableObjBiShortToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiShortToIntFunction<String, Exception> function = ThrowableObjBiShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
