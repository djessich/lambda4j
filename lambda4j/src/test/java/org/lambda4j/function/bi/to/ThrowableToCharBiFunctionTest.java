package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToCharBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToCharBiFunction<String, String, Throwable> function =
                ThrowableToCharBiFunction.of((t, u) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToCharBiFunction<String, String, Throwable> function = ThrowableToCharBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
