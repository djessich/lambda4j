package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleToCharFunction<Throwable> function = ThrowableDoubleToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleToCharFunction<Throwable> function = ThrowableDoubleToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
