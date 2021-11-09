package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriIntToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriIntToLongFunction<Throwable> function =
                ThrowableTriIntToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriIntToLongFunction<Throwable> function = ThrowableTriIntToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
