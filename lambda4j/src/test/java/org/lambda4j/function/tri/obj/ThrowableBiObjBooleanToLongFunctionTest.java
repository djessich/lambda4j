package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBooleanToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanToLongFunction<String, String, Exception> function =
                ThrowableBiObjBooleanToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanToLongFunction<String, String, Exception> function =
                ThrowableBiObjBooleanToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
