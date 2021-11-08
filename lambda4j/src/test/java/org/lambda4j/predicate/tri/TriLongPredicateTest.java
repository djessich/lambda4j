package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriLongPredicate predicate = TriLongPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriLongPredicate predicate = TriLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
