package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTernaryOperator<String, Throwable> operator = ThrowableTernaryOperator.of((t, u, v) -> t);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTernaryOperator<String, Throwable> operator = ThrowableTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
