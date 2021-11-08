package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatToCharFunction<String, String, Exception> function =
                ThrowableBiObjFloatToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatToCharFunction<String, String, Exception> function =
                ThrowableBiObjFloatToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
