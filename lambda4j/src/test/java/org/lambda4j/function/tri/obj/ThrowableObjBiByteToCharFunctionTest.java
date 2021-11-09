package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiByteToCharFunction<String, Throwable> function =
                ThrowableObjBiByteToCharFunction.of((t, value1, value2) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiByteToCharFunction<String, Throwable> function = ThrowableObjBiByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
