package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortBinaryOperator<Throwable> operator = ThrowableShortBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortBinaryOperator<Throwable> operator = ThrowableShortBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
