package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiIntPredicate predicate = BiIntPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiIntPredicate predicate = BiIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
