package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjDoubleToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjDoubleToIntFunction<String, Throwable> function =
                ThrowableObjDoubleToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjDoubleToIntFunction<String, Throwable> function = ThrowableObjDoubleToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
