package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoubleToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoubleToCharFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoubleToCharFunction<String, String, Throwable> function =
                ThrowableBiObjDoubleToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
