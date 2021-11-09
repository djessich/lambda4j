package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiCharToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiCharToFloatFunction<Throwable> function =
                ThrowableBiCharToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiCharToFloatFunction<Throwable> function = ThrowableBiCharToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
