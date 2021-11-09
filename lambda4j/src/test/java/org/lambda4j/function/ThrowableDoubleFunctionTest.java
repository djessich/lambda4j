package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleFunction<String, Throwable> function = ThrowableDoubleFunction.of(Double::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleFunction<String, Throwable> function = ThrowableDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
