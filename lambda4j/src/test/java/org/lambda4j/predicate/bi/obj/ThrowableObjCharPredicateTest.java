package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
