package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiShortPredicate predicate = BiShortPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiShortPredicate predicate = BiShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
