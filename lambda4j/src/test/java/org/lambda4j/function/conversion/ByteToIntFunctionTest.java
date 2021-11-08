package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteToIntFunction function = ByteToIntFunction.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteToIntFunction function = ByteToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
