package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiLongPredicate<String> predicate = ObjBiLongPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiLongPredicate<String> predicate = ObjBiLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
