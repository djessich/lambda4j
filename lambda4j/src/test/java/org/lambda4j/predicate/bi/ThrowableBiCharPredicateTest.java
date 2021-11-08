package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiCharPredicate<Exception> predicate = ThrowableBiCharPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiCharPredicate<Exception> predicate = ThrowableBiCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
