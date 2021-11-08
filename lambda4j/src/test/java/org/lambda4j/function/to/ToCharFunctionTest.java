package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToCharFunction<String> function = ToCharFunction.of(t -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToCharFunction<String> function = ToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
