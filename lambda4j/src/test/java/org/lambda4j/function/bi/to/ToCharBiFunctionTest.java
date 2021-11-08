package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToCharBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToCharBiFunction<String, String> function = ToCharBiFunction.of((t, u) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToCharBiFunction<String, String> function = ToCharBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
