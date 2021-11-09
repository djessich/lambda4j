package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiDoubleToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiDoubleToByteFunction<String, Throwable> function =
                ThrowableObjBiDoubleToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiDoubleToByteFunction<String, Throwable> function = ThrowableObjBiDoubleToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
