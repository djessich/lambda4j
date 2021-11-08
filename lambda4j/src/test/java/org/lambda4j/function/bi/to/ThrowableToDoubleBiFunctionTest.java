package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToDoubleBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToDoubleBiFunction<String, String, Exception> function =
                ThrowableToDoubleBiFunction.of((t, u) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToDoubleBiFunction<String, String, Exception> function = ThrowableToDoubleBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
