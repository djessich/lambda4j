package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBooleanToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBooleanToByteFunction<String, Throwable> function =
                ThrowableObjBiBooleanToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBooleanToByteFunction<String, Throwable> function = ThrowableObjBiBooleanToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
