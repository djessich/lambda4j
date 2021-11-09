package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjCharToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjCharToShortFunction<String, Throwable> function =
                ThrowableObjCharToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjCharToShortFunction<String, Throwable> function = ThrowableObjCharToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
