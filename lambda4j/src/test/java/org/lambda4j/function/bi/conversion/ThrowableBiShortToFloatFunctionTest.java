package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortToFloatFunction<Throwable> function =
                ThrowableBiShortToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortToFloatFunction<Throwable> function = ThrowableBiShortToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
