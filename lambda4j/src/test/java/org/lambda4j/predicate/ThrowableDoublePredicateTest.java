package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoublePredicate<Exception> predicate = ThrowableDoublePredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoublePredicate<Exception> predicate = ThrowableDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
