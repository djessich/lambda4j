package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBooleanToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanToIntFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanToIntFunction<String, String, Throwable> function =
                ThrowableBiObjBooleanToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
