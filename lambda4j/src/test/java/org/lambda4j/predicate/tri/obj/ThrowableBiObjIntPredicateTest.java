package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate =
                ThrowableBiObjIntPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate = ThrowableBiObjIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
