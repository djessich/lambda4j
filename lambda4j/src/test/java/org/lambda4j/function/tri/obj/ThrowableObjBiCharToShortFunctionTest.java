package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiCharToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiCharToShortFunction<String, Exception> function =
                ThrowableObjBiCharToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiCharToShortFunction<String, Exception> function =
                ThrowableObjBiCharToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
