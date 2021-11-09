package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortToCharFunction<Throwable> function = ThrowableShortToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortToCharFunction<Throwable> function = ThrowableShortToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
