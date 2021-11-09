package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjDoubleToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjDoubleToShortFunction<String, Throwable> function =
                ThrowableObjDoubleToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjDoubleToShortFunction<String, Throwable> function = ThrowableObjDoubleToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
