package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiDoublePredicate<String> predicate = ObjBiDoublePredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiDoublePredicate<String> predicate = ObjBiDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
