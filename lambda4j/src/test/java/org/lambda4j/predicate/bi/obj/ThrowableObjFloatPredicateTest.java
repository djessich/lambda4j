package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjFloatPredicate<String, Throwable> predicate = ThrowableObjFloatPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjFloatPredicate<String, Throwable> predicate = ThrowableObjFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
