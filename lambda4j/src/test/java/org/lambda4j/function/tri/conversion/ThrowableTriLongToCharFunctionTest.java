package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriLongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriLongToCharFunction<Exception> function =
                ThrowableTriLongToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriLongToCharFunction<Exception> function = ThrowableTriLongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
