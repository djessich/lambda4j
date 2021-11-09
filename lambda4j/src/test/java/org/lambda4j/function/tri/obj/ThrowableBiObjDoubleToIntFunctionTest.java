package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoubleToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoubleToIntFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoubleToIntFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
