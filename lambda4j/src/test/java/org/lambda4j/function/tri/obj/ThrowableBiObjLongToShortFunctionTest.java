package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongToShortFunction<String, String, Exception> function =
                ThrowableBiObjLongToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongToShortFunction<String, String, Exception> function =
                ThrowableBiObjLongToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
