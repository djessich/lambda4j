package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteToLongFunction function = ByteToLongFunction.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteToLongFunction function = ByteToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}