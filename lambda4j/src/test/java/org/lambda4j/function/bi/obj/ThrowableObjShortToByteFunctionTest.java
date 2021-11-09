package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjShortToByteFunction<String, Throwable> function =
                ThrowableObjShortToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjShortToByteFunction<String, Throwable> function = ThrowableObjShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
