package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate =
                ThrowableObjBiLongPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
