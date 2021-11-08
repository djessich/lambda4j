package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Function2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        Function2<String, String> function = Function2.of(t -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        Function2<String, String> function = Function2.of(null);
        Assertions.assertNull(function);
    }
}
