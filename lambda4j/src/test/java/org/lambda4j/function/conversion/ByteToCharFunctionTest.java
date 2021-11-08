package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteToCharFunction function = ByteToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteToCharFunction function = ByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
