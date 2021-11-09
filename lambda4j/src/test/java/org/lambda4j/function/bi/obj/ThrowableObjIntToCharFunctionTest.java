package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjIntToCharFunction<String, Throwable> function =
                ThrowableObjIntToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjIntToCharFunction<String, Throwable> function = ThrowableObjIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
