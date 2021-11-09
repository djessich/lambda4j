package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiBooleanPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
