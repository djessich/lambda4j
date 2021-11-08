package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriFunction<String, String, String, String> function = TriFunction.of((t, u, v) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriFunction<String, String, String, String> function =
                TriFunction.of((TriFunction<String, String, String, String>) null);
        Assertions.assertNull(function);
    }
}
