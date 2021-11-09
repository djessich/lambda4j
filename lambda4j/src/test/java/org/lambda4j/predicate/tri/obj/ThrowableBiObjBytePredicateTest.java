package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate =
                ThrowableBiObjBytePredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate = ThrowableBiObjBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
