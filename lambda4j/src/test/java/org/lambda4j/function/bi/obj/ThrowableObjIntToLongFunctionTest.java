package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjIntToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjIntToLongFunction<String, Exception> function =
                ThrowableObjIntToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjIntToLongFunction<String, Exception> function = ThrowableObjIntToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
