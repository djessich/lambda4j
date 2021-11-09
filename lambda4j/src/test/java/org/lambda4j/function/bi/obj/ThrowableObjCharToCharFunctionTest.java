package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjCharToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjCharToCharFunction<String, Throwable> function =
                ThrowableObjCharToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjCharToCharFunction<String, Throwable> function = ThrowableObjCharToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
