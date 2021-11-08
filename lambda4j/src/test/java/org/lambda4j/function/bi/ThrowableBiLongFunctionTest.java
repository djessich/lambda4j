package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiLongFunction<String, Exception> function =
                ThrowableBiLongFunction.of((value1, value2) -> Long.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiLongFunction<String, Exception> function =
                ThrowableBiLongFunction.of((ThrowableBiLongFunction<String, Exception>) null);
        Assertions.assertNull(function);
    }
}
