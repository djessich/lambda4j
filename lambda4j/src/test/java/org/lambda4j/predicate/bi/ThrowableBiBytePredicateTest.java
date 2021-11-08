package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiBytePredicate<Exception> predicate = ThrowableBiBytePredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiBytePredicate<Exception> predicate = ThrowableBiBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
