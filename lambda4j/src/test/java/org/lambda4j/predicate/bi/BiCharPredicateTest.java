package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiCharPredicate predicate = BiCharPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiCharPredicate predicate = BiCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
