package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToShortFunction<String, Exception> function = ThrowableToShortFunction.of(Short::parseShort);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToShortFunction<String, Exception> function = ThrowableToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
