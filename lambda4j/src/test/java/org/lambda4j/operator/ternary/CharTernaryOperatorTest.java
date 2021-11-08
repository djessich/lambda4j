package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharTernaryOperator operator = CharTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharTernaryOperator operator = CharTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
