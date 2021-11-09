package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiByteToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiByteToShortFunction<String, Throwable> function =
                ThrowableObjBiByteToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiByteToShortFunction<String, Throwable> function = ThrowableObjBiByteToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
