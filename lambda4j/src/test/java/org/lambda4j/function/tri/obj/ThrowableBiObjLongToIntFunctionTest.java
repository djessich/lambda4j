package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongToIntFunction<String, String, Throwable> function =
                ThrowableBiObjLongToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongToIntFunction<String, String, Throwable> function =
                ThrowableBiObjLongToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
