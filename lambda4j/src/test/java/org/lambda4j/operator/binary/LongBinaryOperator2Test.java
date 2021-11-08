package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongBinaryOperator2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongBinaryOperator2 operator = LongBinaryOperator2.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongBinaryOperator2 operator = LongBinaryOperator2.of(null);
        Assertions.assertNull(operator);
    }
}
