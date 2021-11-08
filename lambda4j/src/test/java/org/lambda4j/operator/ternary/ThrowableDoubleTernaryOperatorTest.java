package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleTernaryOperator<Exception> operator =
                ThrowableDoubleTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleTernaryOperator<Exception> operator = ThrowableDoubleTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
