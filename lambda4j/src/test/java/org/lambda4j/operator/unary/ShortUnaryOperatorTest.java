package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortUnaryOperator operator = ShortUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortUnaryOperator operator = ShortUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
