package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanFunction<String, String, String, Throwable> function =
                ThrowableBiObjBooleanFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanFunction<String, String, String, Throwable> function =
                ThrowableBiObjBooleanFunction.of(null);
        Assertions.assertNull(function);
    }
}
