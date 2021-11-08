package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortTernaryOperator<Exception> operator =
                ThrowableShortTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortTernaryOperator<Exception> operator = ThrowableShortTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
