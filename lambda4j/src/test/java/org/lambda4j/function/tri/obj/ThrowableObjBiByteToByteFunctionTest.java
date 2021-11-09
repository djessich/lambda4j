package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiByteToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiByteToByteFunction<String, Throwable> function =
                ThrowableObjBiByteToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiByteToByteFunction<String, Throwable> function = ThrowableObjBiByteToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
