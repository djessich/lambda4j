package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanFunction<String> function = BooleanFunction.of(Boolean::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanFunction<String> function = BooleanFunction.of(null);
        Assertions.assertNull(function);
    }
}
