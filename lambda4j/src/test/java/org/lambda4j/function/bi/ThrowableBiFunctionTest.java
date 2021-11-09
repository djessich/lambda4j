package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiFunction<String, String, String, Throwable> function = ThrowableBiFunction.of((t, u) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiFunction<String, String, String, Throwable> function =
                ThrowableBiFunction.of((ThrowableBiFunction<String, String, String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
