package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongFunction<String, Throwable> function = ThrowableLongFunction.of(Long::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongFunction<String, Throwable> function = ThrowableLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
