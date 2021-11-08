package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteTernaryOperator operator = ByteTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteTernaryOperator operator = ByteTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
