package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiFloatToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiFloatToCharFunction<Throwable> function = ThrowableBiFloatToCharFunction.of((value1, value2) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiFloatToCharFunction<Throwable> function = ThrowableBiFloatToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
