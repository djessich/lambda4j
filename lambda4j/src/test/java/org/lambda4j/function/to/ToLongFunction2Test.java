package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToLongFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToLongFunction2<String> function = ToLongFunction2.of(Long::parseLong);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToLongFunction2<String> function = ToLongFunction2.of(null);
        Assertions.assertNull(function);
    }
}
