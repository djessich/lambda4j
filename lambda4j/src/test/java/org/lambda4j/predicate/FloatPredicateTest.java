package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatPredicate predicate = FloatPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatPredicate predicate = FloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
