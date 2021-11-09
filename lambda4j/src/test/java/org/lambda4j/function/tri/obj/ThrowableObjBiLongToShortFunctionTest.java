package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiLongToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiLongToShortFunction<String, Throwable> function =
                ThrowableObjBiLongToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiLongToShortFunction<String, Throwable> function = ThrowableObjBiLongToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
