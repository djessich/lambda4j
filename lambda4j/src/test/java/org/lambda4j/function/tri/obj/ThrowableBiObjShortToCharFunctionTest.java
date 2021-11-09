package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortToCharFunction<String, String, Throwable> function =
                ThrowableBiObjShortToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortToCharFunction<String, String, Throwable> function =
                ThrowableBiObjShortToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
