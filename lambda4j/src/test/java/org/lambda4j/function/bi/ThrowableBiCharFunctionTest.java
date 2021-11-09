package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiCharFunction<String, Throwable> function =
                ThrowableBiCharFunction.of((value1, value2) -> Character.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiCharFunction<String, Throwable> function =
                ThrowableBiCharFunction.of((ThrowableBiCharFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
