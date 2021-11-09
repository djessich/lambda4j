package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiIntToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiIntToShortFunction<String, Throwable> function =
                ThrowableObjBiIntToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiIntToShortFunction<String, Throwable> function = ThrowableObjBiIntToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
