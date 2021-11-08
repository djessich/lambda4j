package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToFloatBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToFloatBiFunction<String, String, Exception> function =
                ThrowableToFloatBiFunction.of((t, u) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToFloatBiFunction<String, String, Exception> function = ThrowableToFloatBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
