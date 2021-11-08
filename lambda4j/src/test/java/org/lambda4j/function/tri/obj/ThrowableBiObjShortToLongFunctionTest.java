package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortToLongFunction<String, String, Exception> function =
                ThrowableBiObjShortToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortToLongFunction<String, String, Exception> function =
                ThrowableBiObjShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
