package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleBinaryOperator<Exception> operator =
                ThrowableDoubleBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleBinaryOperator<Exception> operator = ThrowableDoubleBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
