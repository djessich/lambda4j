package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharBinaryOperator operator = CharBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharBinaryOperator operator = CharBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
