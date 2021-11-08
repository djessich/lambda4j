package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBooleanPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBooleanPredicate<String> predicate = ObjBooleanPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBooleanPredicate<String> predicate = ObjBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
