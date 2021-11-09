package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiFloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiFloatToByteFunction<String, Throwable> function =
                ThrowableObjBiFloatToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiFloatToByteFunction<String, Throwable> function = ThrowableObjBiFloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
