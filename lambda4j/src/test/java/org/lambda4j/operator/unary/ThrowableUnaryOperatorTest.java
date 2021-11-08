package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableUnaryOperator<String, Exception> operator = ThrowableUnaryOperator.of(t -> t);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableUnaryOperator<String, Exception> operator = ThrowableUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
