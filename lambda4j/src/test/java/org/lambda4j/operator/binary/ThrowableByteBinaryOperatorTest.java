package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteBinaryOperator<Throwable> operator = ThrowableByteBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteBinaryOperator<Throwable> operator = ThrowableByteBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
