package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongToCharFunction<String, String, Throwable> function =
                ThrowableBiObjLongToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongToCharFunction<String, String, Throwable> function =
                ThrowableBiObjLongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
