package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjDoublePredicate<String, String> predicate = BiObjDoublePredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjDoublePredicate<String, String> predicate = BiObjDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
