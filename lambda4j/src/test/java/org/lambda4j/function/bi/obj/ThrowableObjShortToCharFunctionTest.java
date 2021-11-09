package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjShortToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjShortToCharFunction<String, Throwable> function =
                ThrowableObjShortToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjShortToCharFunction<String, Throwable> function = ThrowableObjShortToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
