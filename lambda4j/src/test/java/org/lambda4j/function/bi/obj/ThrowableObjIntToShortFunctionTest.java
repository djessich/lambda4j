package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjIntToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjIntToShortFunction<String, Throwable> function =
                ThrowableObjIntToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjIntToShortFunction<String, Throwable> function = ThrowableObjIntToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
