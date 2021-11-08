package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntUnaryOperator<Exception> operator = ThrowableIntUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntUnaryOperator<Exception> operator = ThrowableIntUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
