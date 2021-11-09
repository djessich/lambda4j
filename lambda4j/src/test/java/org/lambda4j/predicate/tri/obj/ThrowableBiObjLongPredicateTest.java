package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongPredicate<String, String, Throwable> predicate =
                ThrowableBiObjLongPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongPredicate<String, String, Throwable> predicate = ThrowableBiObjLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
