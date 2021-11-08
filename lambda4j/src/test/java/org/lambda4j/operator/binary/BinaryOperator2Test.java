package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryOperator2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BinaryOperator2<String> operator = BinaryOperator2.of((t, u) -> t);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        BinaryOperator2<String> operator = BinaryOperator2.of(null);
        Assertions.assertNull(operator);
    }
}
