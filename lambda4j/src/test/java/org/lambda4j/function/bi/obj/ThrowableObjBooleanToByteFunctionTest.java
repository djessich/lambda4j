package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanToByteFunction<String, Throwable> function =
                ThrowableObjBooleanToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanToByteFunction<String, Throwable> function = ThrowableObjBooleanToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
