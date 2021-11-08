package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatUnaryOperator<Exception> operator = ThrowableFloatUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatUnaryOperator<Exception> operator = ThrowableFloatUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
