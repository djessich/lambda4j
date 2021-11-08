package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoublePredicate2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoublePredicate2 predicate = DoublePredicate2.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoublePredicate2 predicate = DoublePredicate2.of(null);
        Assertions.assertNull(predicate);
    }
}
