package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
