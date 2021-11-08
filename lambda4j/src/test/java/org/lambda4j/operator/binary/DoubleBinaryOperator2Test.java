package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleBinaryOperator2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleBinaryOperator2 operator = DoubleBinaryOperator2.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleBinaryOperator2 operator = DoubleBinaryOperator2.of(null);
        Assertions.assertNull(operator);
    }
}
