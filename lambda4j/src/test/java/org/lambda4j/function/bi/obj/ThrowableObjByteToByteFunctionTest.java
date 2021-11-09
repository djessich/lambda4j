package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjByteToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjByteToByteFunction<String, Throwable> function =
                ThrowableObjByteToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjByteToByteFunction<String, Throwable> function = ThrowableObjByteToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
