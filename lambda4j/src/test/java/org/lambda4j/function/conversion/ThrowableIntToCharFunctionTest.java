package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntToCharFunction<Throwable> function = ThrowableIntToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntToCharFunction<Throwable> function = ThrowableIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
