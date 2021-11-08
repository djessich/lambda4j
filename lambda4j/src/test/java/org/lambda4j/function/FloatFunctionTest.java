package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatFunction<String> function = FloatFunction.of(Float::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatFunction<String> function = FloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
