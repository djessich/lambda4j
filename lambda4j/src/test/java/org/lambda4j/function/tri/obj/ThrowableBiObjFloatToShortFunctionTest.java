package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatToShortFunction<String, String, Exception> function =
                ThrowableBiObjFloatToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatToShortFunction<String, String, Exception> function =
                ThrowableBiObjFloatToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
