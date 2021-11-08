package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiFloatPredicate<Exception> predicate = ThrowableBiFloatPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiFloatPredicate<Exception> predicate = ThrowableBiFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
