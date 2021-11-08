package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanUnaryOperator operator = BooleanUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanUnaryOperator operator = BooleanUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
