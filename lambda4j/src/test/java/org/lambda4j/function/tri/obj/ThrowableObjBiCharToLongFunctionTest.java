package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiCharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiCharToLongFunction<String, Exception> function =
                ThrowableObjBiCharToLongFunction.of((t, value1, value2) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiCharToLongFunction<String, Exception> function = ThrowableObjBiCharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
