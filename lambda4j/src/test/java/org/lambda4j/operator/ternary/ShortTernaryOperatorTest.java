package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortTernaryOperator operator = ShortTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortTernaryOperator operator = ShortTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
