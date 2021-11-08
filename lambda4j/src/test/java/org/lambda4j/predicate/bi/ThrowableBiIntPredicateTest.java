package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiIntPredicate<Exception> predicate = ThrowableBiIntPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiIntPredicate<Exception> predicate = ThrowableBiIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
