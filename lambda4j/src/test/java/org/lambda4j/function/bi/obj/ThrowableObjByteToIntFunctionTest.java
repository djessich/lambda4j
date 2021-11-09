package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjByteToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjByteToIntFunction<String, Throwable> function =
                ThrowableObjByteToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjByteToIntFunction<String, Throwable> function = ThrowableObjByteToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
