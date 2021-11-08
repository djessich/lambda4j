package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjLongPredicate<String> predicate = ObjLongPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjLongPredicate<String> predicate = ObjLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
