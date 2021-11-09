package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteTernaryOperator<Throwable> operator =
                ThrowableByteTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteTernaryOperator<Throwable> operator = ThrowableByteTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
