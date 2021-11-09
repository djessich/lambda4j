package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriIntPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
