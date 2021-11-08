package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleToCharFunction<Exception> function = ThrowableDoubleToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleToCharFunction<Exception> function = ThrowableDoubleToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
