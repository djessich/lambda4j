package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleTernaryOperator operator = DoubleTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleTernaryOperator operator = DoubleTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
