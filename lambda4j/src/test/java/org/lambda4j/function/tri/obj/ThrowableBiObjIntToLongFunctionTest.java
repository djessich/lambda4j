package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjIntToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntToLongFunction<String, String, Throwable> function =
                ThrowableBiObjIntToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntToLongFunction<String, String, Throwable> function =
                ThrowableBiObjIntToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
