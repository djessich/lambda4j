package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortFunction<String> function = ShortFunction.of(Short::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortFunction<String> function = ShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
