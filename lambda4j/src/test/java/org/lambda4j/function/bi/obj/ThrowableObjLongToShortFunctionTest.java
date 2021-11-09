package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjLongToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjLongToShortFunction<String, Throwable> function =
                ThrowableObjLongToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjLongToShortFunction<String, Throwable> function = ThrowableObjLongToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
