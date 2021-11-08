package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharToIntFunction<Exception> function = ThrowableCharToIntFunction.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharToIntFunction<Exception> function = ThrowableCharToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
