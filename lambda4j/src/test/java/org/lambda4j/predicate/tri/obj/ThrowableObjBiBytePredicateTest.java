package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBytePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate =
                ThrowableObjBiBytePredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate = ThrowableObjBiBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
