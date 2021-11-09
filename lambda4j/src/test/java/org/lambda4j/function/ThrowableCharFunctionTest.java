package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharFunction<String, Throwable> function = ThrowableCharFunction.of(Character::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharFunction<String, Throwable> function = ThrowableCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
