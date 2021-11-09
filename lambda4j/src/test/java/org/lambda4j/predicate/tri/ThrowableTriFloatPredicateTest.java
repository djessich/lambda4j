package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriFloatPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriFloatPredicate<Throwable> predicate =
                ThrowableTriFloatPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
