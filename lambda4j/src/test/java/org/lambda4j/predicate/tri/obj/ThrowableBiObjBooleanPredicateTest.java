package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBooleanPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate = ThrowableBiObjBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
