package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBytePredicate<String, Exception> predicate = ThrowableObjBytePredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBytePredicate<String, Exception> predicate = ThrowableObjBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
