package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteFunction<String> function = ByteFunction.of(Byte::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteFunction<String> function = ByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
