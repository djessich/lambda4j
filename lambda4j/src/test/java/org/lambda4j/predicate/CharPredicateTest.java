package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharPredicate predicate = CharPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharPredicate predicate = CharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
