package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjShortPredicate<String> predicate = ObjShortPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjShortPredicate<String> predicate = ObjShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
