package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongToDoubleFunction<Throwable> function = ThrowableLongToDoubleFunction.of(value -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongToDoubleFunction<Throwable> function = ThrowableLongToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
