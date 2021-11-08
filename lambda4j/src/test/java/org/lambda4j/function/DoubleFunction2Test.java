package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleFunction2<String> function = DoubleFunction2.of(Double::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleFunction2<String> function = DoubleFunction2.of(null);
        Assertions.assertNull(function);
    }
}
