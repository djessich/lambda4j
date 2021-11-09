package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiFloatPredicate<String, Throwable> predicate =
                ThrowableObjBiFloatPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiFloatPredicate<String, Throwable> predicate = ThrowableObjBiFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
