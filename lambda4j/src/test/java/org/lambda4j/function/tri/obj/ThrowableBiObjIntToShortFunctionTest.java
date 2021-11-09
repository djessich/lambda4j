package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjIntToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntToShortFunction<String, String, Throwable> function =
                ThrowableBiObjIntToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntToShortFunction<String, String, Throwable> function =
                ThrowableBiObjIntToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
