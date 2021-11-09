package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleToIntFunction<Throwable> function = ThrowableDoubleToIntFunction.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleToIntFunction<Throwable> function = ThrowableDoubleToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
