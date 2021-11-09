package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiBooleanToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiBooleanToCharFunction<Throwable> function =
                ThrowableBiBooleanToCharFunction.of((value1, value2) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiBooleanToCharFunction<Throwable> function = ThrowableBiBooleanToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
