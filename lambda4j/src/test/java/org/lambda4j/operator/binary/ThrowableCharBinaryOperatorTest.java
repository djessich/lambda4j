package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharBinaryOperator<Throwable> operator = ThrowableCharBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharBinaryOperator<Throwable> operator = ThrowableCharBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
