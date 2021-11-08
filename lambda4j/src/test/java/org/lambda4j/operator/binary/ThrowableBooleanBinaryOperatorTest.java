package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanBinaryOperator<Exception> operator =
                ThrowableBooleanBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanBinaryOperator<Exception> operator = ThrowableBooleanBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
