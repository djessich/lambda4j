package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanToDoubleFunction<Throwable> function = ThrowableBooleanToDoubleFunction.of(value -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanToDoubleFunction<Throwable> function = ThrowableBooleanToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
