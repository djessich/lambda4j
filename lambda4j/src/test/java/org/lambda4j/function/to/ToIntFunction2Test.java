package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToIntFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToIntFunction2<String> function = ToIntFunction2.of(Integer::parseInt);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToIntFunction2<String> function = ToIntFunction2.of(null);
        Assertions.assertNull(function);
    }
}
