package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjByteToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjByteToShortFunction<String, String, Exception> function =
                ThrowableBiObjByteToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjByteToShortFunction<String, String, Exception> function =
                ThrowableBiObjByteToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
