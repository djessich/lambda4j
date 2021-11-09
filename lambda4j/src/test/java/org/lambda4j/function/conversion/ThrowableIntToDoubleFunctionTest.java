package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntToDoubleFunction<Throwable> function = ThrowableIntToDoubleFunction.of(value -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntToDoubleFunction<Throwable> function = ThrowableIntToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
