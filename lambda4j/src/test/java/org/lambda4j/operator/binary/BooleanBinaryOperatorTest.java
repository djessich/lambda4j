package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanBinaryOperator operator = BooleanBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanBinaryOperator operator = BooleanBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
