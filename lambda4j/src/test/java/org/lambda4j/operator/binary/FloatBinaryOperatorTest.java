package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatBinaryOperator operator = FloatBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatBinaryOperator operator = FloatBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
