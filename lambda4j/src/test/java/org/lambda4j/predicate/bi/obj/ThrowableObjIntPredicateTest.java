package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjIntPredicate<String, Throwable> predicate = ThrowableObjIntPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjIntPredicate<String, Throwable> predicate = ThrowableObjIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
