package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongFunction2<String> function = LongFunction2.of(Long::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongFunction2<String> function = LongFunction2.of(null);
        Assertions.assertNull(function);
    }
}
