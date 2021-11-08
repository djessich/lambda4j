package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriIntPredicate predicate = TriIntPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriIntPredicate predicate = TriIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
