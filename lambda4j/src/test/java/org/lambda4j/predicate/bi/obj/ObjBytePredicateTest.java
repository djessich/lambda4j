package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBytePredicate<String> predicate = ObjBytePredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBytePredicate<String> predicate = ObjBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
