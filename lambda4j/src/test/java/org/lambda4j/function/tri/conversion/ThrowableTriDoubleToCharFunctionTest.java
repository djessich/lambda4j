package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriDoubleToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriDoubleToCharFunction<Exception> function =
                ThrowableTriDoubleToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriDoubleToCharFunction<Exception> function = ThrowableTriDoubleToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
