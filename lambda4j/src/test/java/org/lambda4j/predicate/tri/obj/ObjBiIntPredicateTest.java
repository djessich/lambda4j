package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
