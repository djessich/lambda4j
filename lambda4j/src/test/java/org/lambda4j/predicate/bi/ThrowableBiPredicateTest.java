package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
