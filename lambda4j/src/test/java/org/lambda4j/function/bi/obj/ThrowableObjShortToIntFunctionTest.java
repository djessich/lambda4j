package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjShortToIntFunction<String, Throwable> function =
                ThrowableObjShortToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjShortToIntFunction<String, Throwable> function = ThrowableObjShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
