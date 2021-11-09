package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiBooleanToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiBooleanToFloatFunction<Throwable> function =
                ThrowableBiBooleanToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiBooleanToFloatFunction<Throwable> function = ThrowableBiBooleanToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
