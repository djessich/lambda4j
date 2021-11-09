package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriShortToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriShortToCharFunction<Throwable> function =
                ThrowableTriShortToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriShortToCharFunction<Throwable> function = ThrowableTriShortToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
