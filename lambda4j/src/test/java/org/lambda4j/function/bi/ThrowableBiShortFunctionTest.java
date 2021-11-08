package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortFunction<String, Exception> function =
                ThrowableBiShortFunction.of((value1, value2) -> Short.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortFunction<String, Exception> function =
                ThrowableBiShortFunction.of((ThrowableBiShortFunction<String, Exception>) null);
        Assertions.assertNull(function);
    }
}
