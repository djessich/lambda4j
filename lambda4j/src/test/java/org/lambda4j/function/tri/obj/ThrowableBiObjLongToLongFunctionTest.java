package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongToLongFunction<String, String, Throwable> function =
                ThrowableBiObjLongToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongToLongFunction<String, String, Throwable> function =
                ThrowableBiObjLongToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
