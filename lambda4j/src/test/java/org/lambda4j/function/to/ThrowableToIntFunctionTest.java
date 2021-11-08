package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToIntFunction<String, Exception> function = ThrowableToIntFunction.of(Integer::parseInt);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToIntFunction<String, Exception> function = ThrowableToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
