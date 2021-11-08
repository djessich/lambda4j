package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiDoublePredicate<Exception> predicate = ThrowableBiDoublePredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiDoublePredicate<Exception> predicate = ThrowableBiDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
