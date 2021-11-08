package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriLongToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriLongToIntFunction<Exception> function =
                ThrowableTriLongToIntFunction.of((value1, value2, value3) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriLongToIntFunction<Exception> function = ThrowableTriLongToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
