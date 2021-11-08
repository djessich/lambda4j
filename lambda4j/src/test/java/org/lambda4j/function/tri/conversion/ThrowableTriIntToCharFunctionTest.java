package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriIntToCharFunction<Exception> function =
                ThrowableTriIntToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriIntToCharFunction<Exception> function = ThrowableTriIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
