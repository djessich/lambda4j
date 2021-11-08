package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharFunction<String> function = CharFunction.of(Character::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharFunction<String> function = CharFunction.of(null);
        Assertions.assertNull(function);
    }
}
