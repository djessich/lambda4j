package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriBytePredicate<Exception> predicate =
                ThrowableTriBytePredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriBytePredicate<Exception> predicate = ThrowableTriBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
