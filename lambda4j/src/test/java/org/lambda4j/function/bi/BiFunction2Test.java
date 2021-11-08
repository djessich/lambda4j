package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiFunction2<String, String, String> function = BiFunction2.of((t, u) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiFunction2<String, String, String> function = BiFunction2.of((BiFunction2<String, String, String>) null);
        Assertions.assertNull(function);
    }
}
