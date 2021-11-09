package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatUnaryOperator<Throwable> operator = ThrowableFloatUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatUnaryOperator<Throwable> operator = ThrowableFloatUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
