package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriCharFunction<String> function = TriCharFunction.of((value1, value2, value3) -> Character.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriCharFunction<String> function = TriCharFunction.of((TriCharFunction<String>) null);
        Assertions.assertNull(function);
    }
}
