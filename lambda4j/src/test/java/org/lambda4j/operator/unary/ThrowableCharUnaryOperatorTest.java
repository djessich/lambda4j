package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharUnaryOperator<Exception> operator = ThrowableCharUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharUnaryOperator<Exception> operator = ThrowableCharUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
