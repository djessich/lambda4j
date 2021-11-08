package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanUnaryOperator<Exception> operator = ThrowableBooleanUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanUnaryOperator<Exception> operator = ThrowableBooleanUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
