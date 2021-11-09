package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortToIntFunction<Throwable> function = ThrowableShortToIntFunction.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortToIntFunction<Throwable> function = ThrowableShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
