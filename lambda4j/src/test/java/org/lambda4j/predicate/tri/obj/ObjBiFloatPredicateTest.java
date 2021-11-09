package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiFloatPredicate<String> predicate = ObjBiFloatPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiFloatPredicate<String> predicate = ObjBiFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
