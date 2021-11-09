package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiDoubleToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiDoubleToFloatFunction<Throwable> function =
                ThrowableBiDoubleToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiDoubleToFloatFunction<Throwable> function = ThrowableBiDoubleToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
