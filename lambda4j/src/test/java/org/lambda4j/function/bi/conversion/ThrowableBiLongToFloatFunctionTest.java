package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiLongToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiLongToFloatFunction<Throwable> function =
                ThrowableBiLongToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiLongToFloatFunction<Throwable> function = ThrowableBiLongToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
