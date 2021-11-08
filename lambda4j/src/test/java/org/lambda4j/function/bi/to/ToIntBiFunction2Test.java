package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToIntBiFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToIntBiFunction2<String, String> function = ToIntBiFunction2.of((t, u) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToIntBiFunction2<String, String> function = ToIntBiFunction2.of(null);
        Assertions.assertNull(function);
    }
}
