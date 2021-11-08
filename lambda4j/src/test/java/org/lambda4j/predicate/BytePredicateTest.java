package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BytePredicate predicate = BytePredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BytePredicate predicate = BytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
