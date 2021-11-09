package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiIntPredicate<String, Throwable> predicate =
                ThrowableObjBiIntPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiIntPredicate<String, Throwable> predicate = ThrowableObjBiIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
