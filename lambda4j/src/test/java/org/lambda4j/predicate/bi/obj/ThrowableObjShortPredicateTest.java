package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjShortPredicate<String, Throwable> predicate = ThrowableObjShortPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjShortPredicate<String, Throwable> predicate = ThrowableObjShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
