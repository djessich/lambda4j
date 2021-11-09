package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortToIntFunction<String, String, Throwable> function =
                ThrowableBiObjShortToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortToIntFunction<String, String, Throwable> function =
                ThrowableBiObjShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
