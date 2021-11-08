package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriCharPredicate predicate = TriCharPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriCharPredicate predicate = TriCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
