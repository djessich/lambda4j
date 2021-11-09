package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjByteToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjByteToShortFunction<String, Throwable> function =
                ThrowableObjByteToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjByteToShortFunction<String, Throwable> function = ThrowableObjByteToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
