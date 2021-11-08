package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntFunction2<String> function = IntFunction2.of(Integer::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntFunction2<String> function = IntFunction2.of(null);
        Assertions.assertNull(function);
    }
}
