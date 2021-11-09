package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharPredicate<Throwable> predicate = ThrowableCharPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharPredicate<Throwable> predicate = ThrowableCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
