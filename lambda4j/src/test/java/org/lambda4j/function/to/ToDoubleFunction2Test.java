package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToDoubleFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToDoubleFunction2<String> function = ToDoubleFunction2.of(Double::parseDouble);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToDoubleFunction2<String> function = ToDoubleFunction2.of(null);
        Assertions.assertNull(function);
    }
}
