package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToCharFunction<String, Exception> function = ThrowableToCharFunction.of(t -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToCharFunction<String, Exception> function = ThrowableToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
