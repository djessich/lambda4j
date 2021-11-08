package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleUnaryOperator<Exception> operator = ThrowableDoubleUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleUnaryOperator<Exception> operator = ThrowableDoubleUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
