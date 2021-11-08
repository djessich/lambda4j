package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanFunction<String, Exception> function = ThrowableBooleanFunction.of(Boolean::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanFunction<String, Exception> function = ThrowableBooleanFunction.of(null);
        Assertions.assertNull(function);
    }
}
