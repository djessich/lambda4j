package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate =
                ThrowableBiObjFloatPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate = ThrowableBiObjFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
