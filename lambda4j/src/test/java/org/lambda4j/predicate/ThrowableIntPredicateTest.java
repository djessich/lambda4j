package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntPredicate<Throwable> predicate = ThrowableIntPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntPredicate<Throwable> predicate = ThrowableIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
