package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortPredicate<Exception> predicate = ThrowableShortPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortPredicate<Exception> predicate = ThrowableShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
