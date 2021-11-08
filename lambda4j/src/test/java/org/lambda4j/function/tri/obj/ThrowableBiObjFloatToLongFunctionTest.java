package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatToLongFunction<String, String, Exception> function =
                ThrowableBiObjFloatToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatToLongFunction<String, String, Exception> function =
                ThrowableBiObjFloatToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
