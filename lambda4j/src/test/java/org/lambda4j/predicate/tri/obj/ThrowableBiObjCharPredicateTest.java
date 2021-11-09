package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate =
                ThrowableBiObjCharPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate = ThrowableBiObjCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
