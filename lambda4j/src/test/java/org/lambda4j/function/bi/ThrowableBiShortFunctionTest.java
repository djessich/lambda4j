package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortFunction<String, Throwable> function =
                ThrowableBiShortFunction.of((value1, value2) -> Short.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortFunction<String, Throwable> function =
                ThrowableBiShortFunction.of((ThrowableBiShortFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
