package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiCharToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiCharToDoubleFunction<Throwable> function =
                ThrowableBiCharToDoubleFunction.of((value1, value2) -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiCharToDoubleFunction<Throwable> function = ThrowableBiCharToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
