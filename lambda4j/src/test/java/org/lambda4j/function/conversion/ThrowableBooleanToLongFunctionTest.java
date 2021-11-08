package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanToLongFunction<Exception> function = ThrowableBooleanToLongFunction.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanToLongFunction<Exception> function = ThrowableBooleanToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
