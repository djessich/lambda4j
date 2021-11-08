package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiPredicate2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiPredicate2<String, String> predicate = BiPredicate2.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiPredicate2<String, String> predicate = BiPredicate2.of(null);
        Assertions.assertNull(predicate);
    }
}
