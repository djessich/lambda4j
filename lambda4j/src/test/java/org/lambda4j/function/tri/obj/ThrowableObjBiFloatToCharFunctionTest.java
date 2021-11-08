package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiFloatToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiFloatToCharFunction<String, Exception> function =
                ThrowableObjBiFloatToCharFunction.of((t, value1, value2) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiFloatToCharFunction<String, Exception> function = ThrowableObjBiFloatToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
