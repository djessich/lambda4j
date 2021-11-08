package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriFloatPredicate predicate = TriFloatPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriFloatPredicate predicate = TriFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
