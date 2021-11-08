package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntTernaryOperator operator = IntTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntTernaryOperator operator = IntTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
