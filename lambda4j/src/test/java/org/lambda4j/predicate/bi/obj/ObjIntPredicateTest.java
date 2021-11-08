package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
