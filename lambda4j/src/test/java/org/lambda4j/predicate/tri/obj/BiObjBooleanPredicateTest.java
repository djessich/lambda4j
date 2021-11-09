package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjBooleanPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
