package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatTernaryOperator operator = FloatTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatTernaryOperator operator = FloatTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
