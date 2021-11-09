package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate =
                ThrowableObjBiCharPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate = ThrowableObjBiCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
