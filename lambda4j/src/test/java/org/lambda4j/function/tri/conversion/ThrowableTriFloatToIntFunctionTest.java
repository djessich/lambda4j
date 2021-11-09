package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriFloatToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriFloatToIntFunction<Throwable> function =
                ThrowableTriFloatToIntFunction.of((value1, value2, value3) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriFloatToIntFunction<Throwable> function = ThrowableTriFloatToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
