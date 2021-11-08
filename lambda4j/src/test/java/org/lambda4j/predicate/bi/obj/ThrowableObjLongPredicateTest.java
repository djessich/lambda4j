package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjLongPredicate<String, Exception> predicate = ThrowableObjLongPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjLongPredicate<String, Exception> predicate = ThrowableObjLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
