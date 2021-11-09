package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
