package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjCharToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjCharToIntFunction<String, Exception> function =
                ThrowableObjCharToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjCharToIntFunction<String, Exception> function = ThrowableObjCharToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
