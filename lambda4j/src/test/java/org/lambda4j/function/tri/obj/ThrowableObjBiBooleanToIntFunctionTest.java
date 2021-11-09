package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBooleanToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBooleanToIntFunction<String, Throwable> function =
                ThrowableObjBiBooleanToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBooleanToIntFunction<String, Throwable> function = ThrowableObjBiBooleanToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
