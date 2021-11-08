package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjCharToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharToIntFunction<String, String, Exception> function =
                ThrowableBiObjCharToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharToIntFunction<String, String, Exception> function =
                ThrowableBiObjCharToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
