package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
