package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiDoubleFunction<String, Exception> function =
                ThrowableBiDoubleFunction.of((value1, value2) -> Double.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiDoubleFunction<String, Exception> function =
                ThrowableBiDoubleFunction.of((ThrowableBiDoubleFunction<String, Exception>) null);
        Assertions.assertNull(function);
    }
}
