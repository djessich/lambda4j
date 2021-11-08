package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriShortPredicate predicate = TriShortPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriShortPredicate predicate = TriShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
