package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiDoublePredicate predicate = BiDoublePredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiDoublePredicate predicate = BiDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
