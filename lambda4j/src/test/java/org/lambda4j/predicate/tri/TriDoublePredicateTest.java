package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriDoublePredicate predicate = TriDoublePredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriDoublePredicate predicate = TriDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
