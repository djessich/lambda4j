package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TernaryOperator<String> operator = TernaryOperator.of((t, u, v) -> t);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        TernaryOperator<String> operator = TernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
