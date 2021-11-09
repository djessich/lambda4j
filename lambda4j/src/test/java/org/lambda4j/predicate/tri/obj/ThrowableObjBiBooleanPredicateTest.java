package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBooleanPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate =
                ThrowableObjBiBooleanPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate = ThrowableObjBiBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
