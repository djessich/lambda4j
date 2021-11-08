package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToLongBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToLongBiFunction<String, String, Exception> function =
                ThrowableToLongBiFunction.of((t, u) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToLongBiFunction<String, String, Exception> function = ThrowableToLongBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
