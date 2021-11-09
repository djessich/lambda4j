package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiIntFunction<String, Throwable> function =
                ThrowableBiIntFunction.of((value1, value2) -> Integer.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiIntFunction<String, Throwable> function =
                ThrowableBiIntFunction.of((ThrowableBiIntFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
