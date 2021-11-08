package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiLongPredicate predicate = BiLongPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiLongPredicate predicate = BiLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
