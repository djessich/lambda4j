package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate =
                ThrowableObjBiShortPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate = ThrowableObjBiShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
