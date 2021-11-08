package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiIntToCharFunction function = BiIntToCharFunction.of((value1, value2) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiIntToCharFunction function = BiIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
