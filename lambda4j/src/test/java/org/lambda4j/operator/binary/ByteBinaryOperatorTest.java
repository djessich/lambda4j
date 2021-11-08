package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteBinaryOperator operator = ByteBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteBinaryOperator operator = ByteBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
