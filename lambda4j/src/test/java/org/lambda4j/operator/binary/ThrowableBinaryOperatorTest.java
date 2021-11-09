package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBinaryOperator<String, Throwable> operator = ThrowableBinaryOperator.of((t, u) -> t);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBinaryOperator<String, Throwable> operator = ThrowableBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
