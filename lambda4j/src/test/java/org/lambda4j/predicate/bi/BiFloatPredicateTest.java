package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiFloatPredicate predicate = BiFloatPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiFloatPredicate predicate = BiFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
