package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanToIntFunction<String, Throwable> function =
                ThrowableObjBooleanToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanToIntFunction<String, Throwable> function = ThrowableObjBooleanToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
