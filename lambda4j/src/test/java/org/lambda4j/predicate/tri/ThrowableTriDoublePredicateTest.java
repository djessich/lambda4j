package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriDoublePredicate<Throwable> predicate =
                ThrowableTriDoublePredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
