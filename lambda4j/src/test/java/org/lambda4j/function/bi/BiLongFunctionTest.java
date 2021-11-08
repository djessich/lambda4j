package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiLongFunction<String> function = BiLongFunction.of((value1, value2) -> Long.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiLongFunction<String> function = BiLongFunction.of((BiLongFunction<String>) null);
        Assertions.assertNull(function);
    }
}
