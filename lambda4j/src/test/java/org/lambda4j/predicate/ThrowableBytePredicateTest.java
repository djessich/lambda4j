package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBytePredicate<Throwable> predicate = ThrowableBytePredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBytePredicate<Throwable> predicate = ThrowableBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
