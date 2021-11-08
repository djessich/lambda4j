package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongPredicate2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongPredicate2 predicate = LongPredicate2.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongPredicate2 predicate = LongPredicate2.of(null);
        Assertions.assertNull(predicate);
    }
}
