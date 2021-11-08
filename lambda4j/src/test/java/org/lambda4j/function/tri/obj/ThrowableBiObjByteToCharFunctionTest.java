package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjByteToCharFunction<String, String, Exception> function =
                ThrowableBiObjByteToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjByteToCharFunction<String, String, Exception> function =
                ThrowableBiObjByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
