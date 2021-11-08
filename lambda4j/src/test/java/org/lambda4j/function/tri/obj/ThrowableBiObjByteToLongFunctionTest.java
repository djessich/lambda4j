package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjByteToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjByteToLongFunction<String, String, Exception> function =
                ThrowableBiObjByteToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjByteToLongFunction<String, String, Exception> function =
                ThrowableBiObjByteToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
