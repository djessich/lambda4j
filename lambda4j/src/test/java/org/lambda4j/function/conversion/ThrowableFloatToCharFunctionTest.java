package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatToCharFunction<Exception> function = ThrowableFloatToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatToCharFunction<Exception> function = ThrowableFloatToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
