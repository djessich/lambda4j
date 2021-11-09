package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharFunction<String, String, String, Throwable> function =
                ThrowableBiObjCharFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharFunction<String, String, String, Throwable> function = ThrowableBiObjCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
