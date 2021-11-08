package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongTernaryOperator operator = LongTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongTernaryOperator operator = LongTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
