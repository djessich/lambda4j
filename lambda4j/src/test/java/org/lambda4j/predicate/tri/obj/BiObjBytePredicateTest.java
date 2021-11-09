package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
