package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiIntToCharFunction<String, Throwable> function =
                ThrowableObjBiIntToCharFunction.of((t, value1, value2) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiIntToCharFunction<String, Throwable> function = ThrowableObjBiIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
