package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiBytePredicate predicate = BiBytePredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiBytePredicate predicate = BiBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
