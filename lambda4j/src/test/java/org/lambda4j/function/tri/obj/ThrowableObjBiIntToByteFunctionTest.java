package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiIntToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiIntToByteFunction<String, Throwable> function =
                ThrowableObjBiIntToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiIntToByteFunction<String, Throwable> function = ThrowableObjBiIntToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
