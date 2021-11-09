package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanToFloatFunction<Throwable> function = ThrowableBooleanToFloatFunction.of(value -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanToFloatFunction<Throwable> function = ThrowableBooleanToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
