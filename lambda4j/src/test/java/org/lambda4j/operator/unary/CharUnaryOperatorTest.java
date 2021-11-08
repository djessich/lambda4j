package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharUnaryOperator operator = CharUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharUnaryOperator operator = CharUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
