package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
