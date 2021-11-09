package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiShortToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiShortToShortFunction<String, Throwable> function =
                ThrowableObjBiShortToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiShortToShortFunction<String, Throwable> function = ThrowableObjBiShortToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
