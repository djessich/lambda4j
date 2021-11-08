package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanTernaryOperator operator = BooleanTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanTernaryOperator operator = BooleanTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
