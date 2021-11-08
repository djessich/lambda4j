package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatToIntFunction<String, String, Exception> function =
                ThrowableBiObjFloatToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatToIntFunction<String, String, Exception> function =
                ThrowableBiObjFloatToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
