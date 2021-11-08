package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBytePredicate<Exception> predicate = ThrowableBytePredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBytePredicate<Exception> predicate = ThrowableBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
