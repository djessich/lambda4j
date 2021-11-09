package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiCharToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiCharToCharFunction<String, Throwable> function =
                ThrowableObjBiCharToCharFunction.of((t, value1, value2) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiCharToCharFunction<String, Throwable> function = ThrowableObjBiCharToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
