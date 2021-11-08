package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntTernaryOperator<Exception> operator =
                ThrowableIntTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntTernaryOperator<Exception> operator = ThrowableIntTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
