package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
