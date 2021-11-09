package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiDoubleToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiDoubleToShortFunction<String, Throwable> function =
                ThrowableObjBiDoubleToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiDoubleToShortFunction<String, Throwable> function = ThrowableObjBiDoubleToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
