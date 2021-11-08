package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongTernaryOperator<Exception> operator =
                ThrowableLongTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongTernaryOperator<Exception> operator = ThrowableLongTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
