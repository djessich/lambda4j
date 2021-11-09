package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortToShortFunction<String, String, Throwable> function =
                ThrowableBiObjShortToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortToShortFunction<String, String, Throwable> function =
                ThrowableBiObjShortToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
