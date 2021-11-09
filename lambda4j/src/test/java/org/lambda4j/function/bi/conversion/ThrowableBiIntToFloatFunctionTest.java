package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiIntToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiIntToFloatFunction<Throwable> function = ThrowableBiIntToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiIntToFloatFunction<Throwable> function = ThrowableBiIntToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
