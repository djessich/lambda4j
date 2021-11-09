package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
