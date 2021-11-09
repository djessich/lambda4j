package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongToCharFunction<Throwable> function = ThrowableLongToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongToCharFunction<Throwable> function = ThrowableLongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
