package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriCharToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriCharToIntFunction<Throwable> function =
                ThrowableTriCharToIntFunction.of((value1, value2, value3) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriCharToIntFunction<Throwable> function = ThrowableTriCharToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
