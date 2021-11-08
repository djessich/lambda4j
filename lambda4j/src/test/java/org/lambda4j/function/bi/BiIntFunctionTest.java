package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiIntFunction<String> function = BiIntFunction.of((value1, value2) -> Integer.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiIntFunction<String> function = BiIntFunction.of((BiIntFunction<String>) null);
        Assertions.assertNull(function);
    }
}
