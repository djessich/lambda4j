package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiBooleanToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiBooleanToLongFunction<Throwable> function =
                ThrowableBiBooleanToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiBooleanToLongFunction<Throwable> function = ThrowableBiBooleanToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
