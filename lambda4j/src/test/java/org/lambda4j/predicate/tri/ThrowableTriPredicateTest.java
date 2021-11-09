package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate =
                ThrowableTriPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate = ThrowableTriPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
