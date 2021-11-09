package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToIntBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToIntBiFunction<String, String, Throwable> function =
                ThrowableToIntBiFunction.of((t, u) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToIntBiFunction<String, String, Throwable> function = ThrowableToIntBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
