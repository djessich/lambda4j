package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntBinaryOperator<Throwable> operator = ThrowableIntBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntBinaryOperator<Throwable> operator = ThrowableIntBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
