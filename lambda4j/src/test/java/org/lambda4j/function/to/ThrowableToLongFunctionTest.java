package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToLongFunction<String, Throwable> function = ThrowableToLongFunction.of(Long::parseLong);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToLongFunction<String, Throwable> function = ThrowableToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
