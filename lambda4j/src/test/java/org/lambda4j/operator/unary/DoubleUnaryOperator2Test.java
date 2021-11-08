package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleUnaryOperator2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleUnaryOperator2 operator = DoubleUnaryOperator2.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleUnaryOperator2 operator = DoubleUnaryOperator2.of(null);
        Assertions.assertNull(operator);
    }
}
