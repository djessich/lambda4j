package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
