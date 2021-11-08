package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongUnaryOperator2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongUnaryOperator2 operator = LongUnaryOperator2.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongUnaryOperator2 operator = LongUnaryOperator2.of(null);
        Assertions.assertNull(operator);
    }
}
