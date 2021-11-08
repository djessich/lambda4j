package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongFunction<String, String, String, Exception> function =
                ThrowableBiObjLongFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongFunction<String, String, String, Exception> function = ThrowableBiObjLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
