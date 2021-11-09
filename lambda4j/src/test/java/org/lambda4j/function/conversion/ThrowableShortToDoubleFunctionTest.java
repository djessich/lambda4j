package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortToDoubleFunction<Throwable> function = ThrowableShortToDoubleFunction.of(value -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortToDoubleFunction<Throwable> function = ThrowableShortToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
