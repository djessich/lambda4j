package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatUnaryOperator operator = FloatUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatUnaryOperator operator = FloatUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
