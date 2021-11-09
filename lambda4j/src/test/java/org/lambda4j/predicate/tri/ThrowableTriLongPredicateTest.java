package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriLongPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriLongPredicate<Throwable> predicate =
                ThrowableTriLongPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
