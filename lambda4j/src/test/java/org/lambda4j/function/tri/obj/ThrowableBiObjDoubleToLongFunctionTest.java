package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoubleToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoubleToLongFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoubleToLongFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
