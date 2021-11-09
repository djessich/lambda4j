package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiByteToCharFunction<Throwable> function = ThrowableBiByteToCharFunction.of((value1, value2) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiByteToCharFunction<Throwable> function = ThrowableBiByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
