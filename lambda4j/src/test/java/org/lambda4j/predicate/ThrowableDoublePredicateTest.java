package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoublePredicate<Throwable> predicate = ThrowableDoublePredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoublePredicate<Throwable> predicate = ThrowableDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
