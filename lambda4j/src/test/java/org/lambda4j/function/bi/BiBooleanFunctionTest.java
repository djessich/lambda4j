package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiBooleanFunction<String> function = BiBooleanFunction.of((value1, value2) -> Boolean.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiBooleanFunction<String> function = BiBooleanFunction.of((BiBooleanFunction<String>) null);
        Assertions.assertNull(function);
    }
}
