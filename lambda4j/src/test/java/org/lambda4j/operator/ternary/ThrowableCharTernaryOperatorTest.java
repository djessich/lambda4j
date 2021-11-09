package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharTernaryOperator<Throwable> operator =
                ThrowableCharTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharTernaryOperator<Throwable> operator = ThrowableCharTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
