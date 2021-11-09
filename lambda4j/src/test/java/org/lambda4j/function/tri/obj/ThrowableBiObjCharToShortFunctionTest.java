package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjCharToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharToShortFunction<String, String, Throwable> function =
                ThrowableBiObjCharToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharToShortFunction<String, String, Throwable> function =
                ThrowableBiObjCharToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
