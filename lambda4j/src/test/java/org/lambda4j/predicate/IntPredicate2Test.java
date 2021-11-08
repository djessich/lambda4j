package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntPredicate2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntPredicate2 predicate = IntPredicate2.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntPredicate2 predicate = IntPredicate2.of(null);
        Assertions.assertNull(predicate);
    }
}
