package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjFloatToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjFloatToIntFunction<String, Throwable> function =
                ThrowableObjFloatToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjFloatToIntFunction<String, Throwable> function = ThrowableObjFloatToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
