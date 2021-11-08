package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
