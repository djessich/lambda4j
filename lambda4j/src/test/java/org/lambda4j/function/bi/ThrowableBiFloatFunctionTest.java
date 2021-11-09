package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiDoubleFunction<String, Throwable> function =
                ThrowableBiDoubleFunction.of((value1, value2) -> Double.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiDoubleFunction<String, Throwable> function =
                ThrowableBiDoubleFunction.of((ThrowableBiDoubleFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
