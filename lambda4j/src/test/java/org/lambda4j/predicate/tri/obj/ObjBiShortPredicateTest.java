package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiShortPredicate<String> predicate = ObjBiShortPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiShortPredicate<String> predicate = ObjBiShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
