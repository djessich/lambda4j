package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriByteToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriByteToLongFunction<Throwable> function =
                ThrowableTriByteToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriByteToLongFunction<Throwable> function = ThrowableTriByteToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
