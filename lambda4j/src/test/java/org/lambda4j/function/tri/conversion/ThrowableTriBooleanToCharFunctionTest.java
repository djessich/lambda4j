package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriBooleanToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriBooleanToCharFunction<Exception> function =
                ThrowableTriBooleanToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriBooleanToCharFunction<Exception> function = ThrowableTriBooleanToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
