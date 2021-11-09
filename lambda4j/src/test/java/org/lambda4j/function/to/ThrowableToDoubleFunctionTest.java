package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToDoubleFunction<String, Throwable> function = ThrowableToDoubleFunction.of(Double::parseDouble);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToDoubleFunction<String, Throwable> function = ThrowableToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
