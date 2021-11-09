package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBooleanFunction<String, String, Throwable> function =
                ThrowableObjBiBooleanFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBooleanFunction<String, String, Throwable> function = ThrowableObjBiBooleanFunction.of(null);
        Assertions.assertNull(function);
    }
}
