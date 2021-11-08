package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortBinaryOperator operator = ShortBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortBinaryOperator operator = ShortBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
