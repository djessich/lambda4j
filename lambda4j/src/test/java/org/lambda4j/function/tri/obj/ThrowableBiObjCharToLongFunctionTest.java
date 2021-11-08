package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjCharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharToLongFunction<String, String, Exception> function =
                ThrowableBiObjCharToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharToLongFunction<String, String, Exception> function =
                ThrowableBiObjCharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
