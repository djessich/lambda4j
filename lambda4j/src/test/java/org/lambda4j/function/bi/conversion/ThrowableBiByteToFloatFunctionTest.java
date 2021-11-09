package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiByteToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiByteToFloatFunction<Throwable> function =
                ThrowableBiByteToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiByteToFloatFunction<Throwable> function = ThrowableBiByteToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
