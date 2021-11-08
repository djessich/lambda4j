package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowablePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowablePredicate<String, Exception> predicate = ThrowablePredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowablePredicate<String, Exception> predicate = ThrowablePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
