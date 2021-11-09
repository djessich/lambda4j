package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjDoubleToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjDoubleToByteFunction<String, Throwable> function =
                ThrowableObjDoubleToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjDoubleToByteFunction<String, Throwable> function = ThrowableObjDoubleToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
