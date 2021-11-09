package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjDoublePredicate<String, Throwable> predicate = ThrowableObjDoublePredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjDoublePredicate<String, Throwable> predicate = ThrowableObjDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
