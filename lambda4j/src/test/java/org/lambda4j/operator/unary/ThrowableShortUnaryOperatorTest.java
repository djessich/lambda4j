package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortUnaryOperator<Exception> operator = ThrowableShortUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortUnaryOperator<Exception> operator = ThrowableShortUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}