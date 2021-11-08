package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteTernaryOperator<Exception> operator =
                ThrowableByteTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteTernaryOperator<Exception> operator = ThrowableByteTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
