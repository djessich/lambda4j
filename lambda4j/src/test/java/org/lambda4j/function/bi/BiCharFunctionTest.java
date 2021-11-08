package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiCharFunction<String> function = BiCharFunction.of((value1, value2) -> Character.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiCharFunction<String> function = BiCharFunction.of((BiCharFunction<String>) null);
        Assertions.assertNull(function);
    }
}
