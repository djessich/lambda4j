package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriShortPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriShortPredicate<Exception> predicate =
                ThrowableTriShortPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriShortPredicate<Exception> predicate = ThrowableTriShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
