package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteUnaryOperator operator = ByteUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteUnaryOperator operator = ByteUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
