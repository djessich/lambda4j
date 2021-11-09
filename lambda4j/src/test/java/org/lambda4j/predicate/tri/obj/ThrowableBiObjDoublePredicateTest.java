package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate =
                ThrowableBiObjDoublePredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate = ThrowableBiObjDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
