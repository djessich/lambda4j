package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanTernaryOperator<Throwable> operator =
                ThrowableBooleanTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanTernaryOperator<Throwable> operator = ThrowableBooleanTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
