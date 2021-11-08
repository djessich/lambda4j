package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortFunction<String, Exception> function = ThrowableShortFunction.of(Short::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortFunction<String, Exception> function = ThrowableShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
