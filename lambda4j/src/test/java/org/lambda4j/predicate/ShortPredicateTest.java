package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortPredicate predicate = ShortPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortPredicate predicate = ShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
