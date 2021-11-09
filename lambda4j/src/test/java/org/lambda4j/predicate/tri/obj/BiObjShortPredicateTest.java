package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
