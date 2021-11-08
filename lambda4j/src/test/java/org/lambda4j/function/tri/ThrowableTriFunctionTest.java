package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriFunction<String, String, String, String, Exception> function =
                ThrowableTriFunction.of((t, u, v) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriFunction<String, String, String, String, Exception> function =
                ThrowableTriFunction.of((ThrowableTriFunction<String, String, String, String, Exception>) null);
        Assertions.assertNull(function);
    }
}
