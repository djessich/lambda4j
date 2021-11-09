package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiDoublePredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiDoublePredicate<String, Throwable> predicate =
                ThrowableObjBiDoublePredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiDoublePredicate<String, Throwable> predicate = ThrowableObjBiDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
