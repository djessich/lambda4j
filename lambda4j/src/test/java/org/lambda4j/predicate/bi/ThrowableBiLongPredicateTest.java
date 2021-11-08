package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiLongPredicate<Exception> predicate = ThrowableBiLongPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiLongPredicate<Exception> predicate = ThrowableBiLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
