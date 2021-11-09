package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortPredicate<String, String, Throwable> predicate =
                ThrowableBiObjShortPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortPredicate<String, String, Throwable> predicate = ThrowableBiObjShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
