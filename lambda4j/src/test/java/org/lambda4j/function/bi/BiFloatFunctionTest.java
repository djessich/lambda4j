package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiFloatFunction<String> function = BiFloatFunction.of((value1, value2) -> Float.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiFloatFunction<String> function = BiFloatFunction.of((BiFloatFunction<String>) null);
        Assertions.assertNull(function);
    }
}
