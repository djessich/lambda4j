package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteUnaryOperator<Exception> operator = ThrowableByteUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteUnaryOperator<Exception> operator = ThrowableByteUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
