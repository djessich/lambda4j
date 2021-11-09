package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriFloatToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriFloatToCharFunction<Throwable> function =
                ThrowableTriFloatToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriFloatToCharFunction<Throwable> function = ThrowableTriFloatToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
