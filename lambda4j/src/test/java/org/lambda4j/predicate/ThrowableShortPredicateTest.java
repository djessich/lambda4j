package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortPredicate<Throwable> predicate = ThrowableShortPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortPredicate<Throwable> predicate = ThrowableShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
