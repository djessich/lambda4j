package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanToCharFunction<Throwable> function = ThrowableBooleanToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanToCharFunction<Throwable> function = ThrowableBooleanToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
