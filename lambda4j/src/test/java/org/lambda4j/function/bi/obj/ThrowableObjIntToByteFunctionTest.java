package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjIntToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjIntToByteFunction<String, Throwable> function =
                ThrowableObjIntToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjIntToByteFunction<String, Throwable> function = ThrowableObjIntToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
