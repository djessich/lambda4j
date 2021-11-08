package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToShortFunction<String> function = ToShortFunction.of(Short::parseShort);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToShortFunction<String> function = ToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
