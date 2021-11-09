package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBooleanToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBooleanToCharFunction<String, Throwable> function =
                ThrowableObjBiBooleanToCharFunction.of((t, value1, value2) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBooleanToCharFunction<String, Throwable> function = ThrowableObjBiBooleanToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
