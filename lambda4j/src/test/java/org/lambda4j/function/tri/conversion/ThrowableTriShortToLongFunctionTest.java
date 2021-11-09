package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriShortToLongFunction<Throwable> function =
                ThrowableTriShortToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriShortToLongFunction<Throwable> function = ThrowableTriShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
