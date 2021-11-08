package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToLongBiFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToLongBiFunction2<String, String> function = ToLongBiFunction2.of((t, u) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToLongBiFunction2<String, String> function = ToLongBiFunction2.of(null);
        Assertions.assertNull(function);
    }
}
