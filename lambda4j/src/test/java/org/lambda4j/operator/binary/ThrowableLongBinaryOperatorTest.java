package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongBinaryOperator<Throwable> operator = ThrowableLongBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongBinaryOperator<Throwable> operator = ThrowableLongBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
