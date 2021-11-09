package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortFunction<String, String, String, Throwable> function =
                ThrowableBiObjShortFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortFunction<String, String, String, Throwable> function = ThrowableBiObjShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
