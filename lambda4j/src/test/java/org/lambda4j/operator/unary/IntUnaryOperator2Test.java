package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntUnaryOperator2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntUnaryOperator2 operator = IntUnaryOperator2.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntUnaryOperator2 operator = IntUnaryOperator2.of(null);
        Assertions.assertNull(operator);
    }
}
