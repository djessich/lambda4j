package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnaryOperator2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        UnaryOperator2<String> operator = UnaryOperator2.of(t -> t);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        UnaryOperator2<String> operator = UnaryOperator2.of(null);
        Assertions.assertNull(operator);
    }
}
