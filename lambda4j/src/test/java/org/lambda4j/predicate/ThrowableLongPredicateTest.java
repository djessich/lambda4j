package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongPredicate<Exception> predicate = ThrowableLongPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongPredicate<Exception> predicate = ThrowableLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
