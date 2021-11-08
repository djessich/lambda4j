package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoubleFunction<String, String, String, Exception> function =
                ThrowableBiObjDoubleFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoubleFunction<String, String, String, Exception> function =
                ThrowableBiObjDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
