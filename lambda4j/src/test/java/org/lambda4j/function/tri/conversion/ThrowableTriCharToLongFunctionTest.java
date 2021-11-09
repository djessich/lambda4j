package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriCharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriCharToLongFunction<Throwable> function =
                ThrowableTriCharToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriCharToLongFunction<Throwable> function = ThrowableTriCharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
