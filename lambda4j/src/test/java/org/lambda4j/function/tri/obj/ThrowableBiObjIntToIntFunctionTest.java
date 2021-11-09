package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjIntToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntToIntFunction<String, String, Throwable> function =
                ThrowableBiObjIntToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntToIntFunction<String, String, Throwable> function =
                ThrowableBiObjIntToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
