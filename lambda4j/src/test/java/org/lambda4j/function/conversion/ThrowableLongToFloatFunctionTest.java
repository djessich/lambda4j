package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongToFloatFunction<Throwable> function = ThrowableLongToFloatFunction.of(value -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongToFloatFunction<Throwable> function = ThrowableLongToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
