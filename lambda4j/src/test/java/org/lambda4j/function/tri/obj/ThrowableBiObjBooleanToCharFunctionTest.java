package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBooleanToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanToCharFunction<String, String, Exception> function =
                ThrowableBiObjBooleanToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanToCharFunction<String, String, Exception> function =
                ThrowableBiObjBooleanToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
