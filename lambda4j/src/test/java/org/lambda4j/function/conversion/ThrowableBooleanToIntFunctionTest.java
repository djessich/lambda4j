package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanToIntFunction<Exception> function = ThrowableBooleanToIntFunction.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanToIntFunction<Exception> function = ThrowableBooleanToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
