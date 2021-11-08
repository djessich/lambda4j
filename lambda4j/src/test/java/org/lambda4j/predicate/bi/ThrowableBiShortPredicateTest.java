package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortPredicate<Exception> predicate = ThrowableBiShortPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortPredicate<Exception> predicate = ThrowableBiShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
