package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjFloatToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjFloatToShortFunction<String, Throwable> function =
                ThrowableObjFloatToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjFloatToShortFunction<String, Throwable> function = ThrowableObjFloatToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
