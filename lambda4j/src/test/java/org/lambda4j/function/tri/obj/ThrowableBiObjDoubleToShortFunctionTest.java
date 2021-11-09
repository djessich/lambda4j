package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoubleToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoubleToShortFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoubleToShortFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
