package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntFunction<String, Exception> function = ThrowableIntFunction.of(Integer::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntFunction<String, Exception> function = ThrowableIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
