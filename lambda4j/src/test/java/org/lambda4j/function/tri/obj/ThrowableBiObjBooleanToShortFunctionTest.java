package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBooleanToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanToShortFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanToShortFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
