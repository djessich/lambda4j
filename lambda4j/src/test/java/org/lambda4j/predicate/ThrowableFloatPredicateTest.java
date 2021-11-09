package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatPredicate<Throwable> predicate = ThrowableFloatPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatPredicate<Throwable> predicate = ThrowableFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
