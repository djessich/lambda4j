package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiLongToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiLongToByteFunction<String, Throwable> function =
                ThrowableObjBiLongToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiLongToByteFunction<String, Throwable> function = ThrowableObjBiLongToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
