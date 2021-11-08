package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
