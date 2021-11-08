package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatTernaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatTernaryOperator<Exception> operator =
                ThrowableFloatTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatTernaryOperator<Exception> operator = ThrowableFloatTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
