package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToShortBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToShortBiFunction<String, String, Throwable> function =
                ThrowableToShortBiFunction.of((t, u) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToShortBiFunction<String, String, Throwable> function = ThrowableToShortBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
